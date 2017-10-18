package co.hypcode.tasknode;

import java.io.File;
import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.hypcode.tasknode.config.ConsoleConfig;
import co.hypcode.tasknode.config.TaskNodeConfig;

/**
 * Main class.
 */
public class Application implements SystemConfig {
   // Base URI the Grizzly HTTP server will listen on
   public static final String BASE_URI = System.getProperty(PROP_BASE_URI, PROP_BASE_URI_DEFAULT);
   public static final String CONFIG_PATH = System.getProperty(PROP_CONFIG_PATH, PROP_CONFIG_PATH_DEFAULT);
   private static Logger logger = LoggerFactory.getLogger(Application.class);

   /**
    * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
    * 
    * @return Grizzly HTTP server.
    * @throws IOException
    * @throws IllegalArgumentException
    */
   public static HttpServer startServer(TaskNode taskNode, TaskNodeConfig config) throws IllegalArgumentException, IOException {
      ConsoleConfig consoleConfig = config.getConsoleConfig();
      if (consoleConfig == null) {
         throw new IllegalArgumentException("consoleConfig is required");
      }
      // create a resource config that scans for JAX-RS resources and providers
      // in com.example.rest package
      final ResourceConfig rc = new ResourceConfig().packages(true, RESOURCES_PACKAGE);
      rc.register(JacksonFeature.class);
      rc.register(new AbstractBinder() {
         @Override
         protected void configure() {
            bind(taskNode.getRedisson()).to(RedissonClient.class);
         }
      });
      // Initialize and register Jersey Servlet
      // create and start a new instance of grizzly http server
      // exposing the Jersey application at BASE_URI
      logger.info("Creating HTTP server on {}", consoleConfig.getBindAddress());
      HttpServer server = GrizzlyHttpServerFactory.createHttpServer(consoleConfig.getBindAddress(), rc);
      server.start();
      logger.info("Console node has started");
      // logger.info("WADL available at {}/application.wadl", consoleConfig.getBindAddress());
      Runtime.getRuntime().addShutdownHook(new Thread() {
         @Override
         public void run() {
            logger.info("Console node has stopped");
            server.shutdown();
         }
      });
      return server;
   }

   public static TaskNode startTaskNode(TaskNodeConfig config) throws IOException {
      return new TaskNode(config).start();
   }

   /**
    * Main method.
    * 
    * @param args
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
      // Redisson config
      String configPath = args.length > 0 ? args[0] : null;
      if (configPath == null)
         configPath = System.getProperty(PROP_CONFIG_PATH, PROP_CONFIG_PATH_DEFAULT);
      TaskNodeConfig config = TaskNodeConfig.fromYAML(new File(configPath));

      startServer(startTaskNode(config), config);

      System.in.read();
   }
}
