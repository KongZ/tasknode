/*
 * Licensed to BBTV NEW MEDIA under HYPCODE CO.LTD. license 
 * agreements. See the NOTICE file distributed with this work 
 * for additional information regarding copyright ownership.
 *
 * Unauthorized copying of this file, via any medium is strictly 
 * prohibited proprietary and confidential.
 *
 */

package co.hypcode.tasknode;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnection;
import org.redisson.connection.ConnectionManager;
import org.redisson.connection.MasterSlaveEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.hypcode.tasknode.config.TaskNodeConfig;

/**
 * @author Kong
 */
public class TaskNode implements SystemConfig {

   private static final Logger logger = LoggerFactory.getLogger(TaskNode.class);

   private final RedissonClient redisson;
   private final Node node;

   private InetSocketAddress remoteAddress;
   private InetSocketAddress localAddress;

   public TaskNode(@NotNull TaskNodeConfig config) {
      assert config != null;
      this.node = new Node();
      this.node.setId(generateId());
      if (config.getExecutorServiceWorkers() == null) {
         Map<String, Integer> workers = new HashMap<>();
         workers.put(DEFAULT_WORKER_NAME, 20);
         config.setExecutorServiceWorkers(workers);
      }
      this.node.setMaxWorker(config.getExecutorServiceWorkers());
      this.redisson = Redisson.create(config);
   }

   public RedissonClient getRedisson() {
      return redisson;
   }

   public InetSocketAddress getLocalAddress() {
      return localAddress;
   }

   public InetSocketAddress getRemoteAddress() {
      return remoteAddress;
   }

   public String getId() {
      return node.getId();
   }

   protected String generateId() {
      UUID uuid = UUID.randomUUID();
      long mostSigBits = uuid.getMostSignificantBits();
      long leastSigBits = uuid.getLeastSignificantBits();
      return new StringBuffer().append(digits(mostSigBits >> 32, 8)).append(digits(mostSigBits >> 16, 4)).append(digits(mostSigBits, 4))
               .append(digits(leastSigBits >> 48, 4)).append(digits(leastSigBits, 12)).toString();
   }

   /** Returns value represented by the specified number of hex digits. */
   private static String digits(long val, int digits) {
      long hi = 1L << (digits * 4);
      return Long.toHexString(hi | (val & (hi - 1))).substring(1);
   }

   /**
    * Shutdown Redisson node instance
    */
   public void shutdown() {
      if (redisson != null) {
         try {
            redisson.getSet(KEY_NODE_LIST).remove(node);
         }
         catch (Exception e) {
            logger.error(e.getMessage());
         }
         redisson.shutdown();
      }
      logger.info("Task node has been shutdown");
   }

   /**
    * Start Redisson node instance
    */
   public TaskNode start() {
      ConnectionManager connectionManager = ((Redisson)this.redisson).getConnectionManager();
      for (MasterSlaveEntry entry : connectionManager.getEntrySet()) {
         RFuture<RedisConnection> readFuture = entry.connectionReadOp(null);
         if (readFuture.awaitUninterruptibly((long)connectionManager.getConfig().getConnectTimeout()) && readFuture.isSuccess()) {
            RedisConnection connection = readFuture.getNow();
            entry.releaseRead(connection);
            this.remoteAddress = (InetSocketAddress)connection.getChannel().remoteAddress();
            this.localAddress = (InetSocketAddress)connection.getChannel().localAddress();
            break;
         }
         RFuture<RedisConnection> writeFuture = entry.connectionWriteOp(null);
         if (writeFuture.awaitUninterruptibly((long)connectionManager.getConfig().getConnectTimeout()) && writeFuture.isSuccess()) {
            RedisConnection connection = writeFuture.getNow();
            entry.releaseWrite(connection);
            this.remoteAddress = (InetSocketAddress)connection.getChannel().remoteAddress();
            this.localAddress = (InetSocketAddress)connection.getChannel().localAddress();
            break;
         }
      }
      // set worker
      for (Entry<String, Integer> entry : this.node.getMaxWorker().entrySet()) {
         String name = entry.getKey();
         int maxWorker = entry.getValue();
         this.redisson.getExecutorService(name).registerWorkers(maxWorker);
         logger.info("Start '{}' with {} workers", name, maxWorker);
      }
      this.redisson.getSet(KEY_NODE_LIST).add(node);
      logger.info("Task node has started!");
      // add web hook to shutdown
      Runtime.getRuntime().addShutdownHook(new Thread() {
         @Override
         public void run() {
            shutdown();
         }
      });
      return this;
   }

}
