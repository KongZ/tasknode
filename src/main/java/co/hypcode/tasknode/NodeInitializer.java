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

import org.redisson.RedissonNode;
import org.redisson.api.Node;
import org.redisson.api.NodesGroup;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonNodeInitializer;
import org.redisson.connection.ConnectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kong
 */
public class NodeInitializer implements RedissonNodeInitializer, ConnectionListener {
   private static Logger logger = LoggerFactory.getLogger(Application.class);

   @Override
   public void onStartup(RedissonNode redissonNode) {
      RedissonClient redisson = redissonNode.getRedisson();
      NodesGroup<Node> nodesGroup = redisson.getNodesGroup();
      nodesGroup.addConnectionListener(this);
      // redisson.getRemoteService("myRemoteService").register(MyRemoteService.class, new
      // MyRemoteServiceImpl(...));
      StringBuilder eventTopic = new StringBuilder();
      eventTopic.append(redissonNode.getId()).append(" has joined.").append(" remote-server:").append(redissonNode.getRemoteAddress());
      logger.info(eventTopic.toString());
      redisson.getTopic("TaskNodeNotificationTopic").publish(eventTopic.toString());
   }

   @Override
   public void onConnect(InetSocketAddress addr) {
      logger.info("Connected to {}", addr);
   }

   @Override
   public void onDisconnect(InetSocketAddress addr) {
      logger.info("Disconnected from {}", addr);
   }
}
