/*
 * Licensed to BBTV NEW MEDIA under HYPCODE CO.LTD. license 
 * agreements. See the NOTICE file distributed with this work 
 * for additional information regarding copyright ownership.
 *
 * Unauthorized copying of this file, via any medium is strictly 
 * prohibited proprietary and confidential.
 *
 */

package co.hypcode.tasknode.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.redisson.config.Config;
import org.redisson.config.ConfigSupport;

/**
 * @author Kong
 */
public class TaskNodeConfig extends Config {
   private ConsoleConfig consoleConfig;

   private Map<String, Integer> executorServiceWorkers = new HashMap<String, Integer>();

   public TaskNodeConfig() {
      super();
   }

   /**
    * Read config object stored in YAML format from <code>File</code>
    *
    * @param file object
    * @return config
    * @throws IOException error
    */
   public static TaskNodeConfig fromYAML(File file) throws IOException {
      ConfigSupport support = new ConfigSupport();
      return support.fromYAML(file, TaskNodeConfig.class);
   }

   /**
    * Gets consoleConfig.
    * 
    * @return the consoleConfig
    */
   public ConsoleConfig getConsoleConfig() {
      return consoleConfig;
   }

   /**
    * Sets consoleConfig.
    * 
    * @param consoleConfig the consoleConfig to set
    */
   public void setConsoleConfig(ConsoleConfig consoleConfig) {
      this.consoleConfig = consoleConfig;
   }

   /**
    * Executor service workers amount per service name
    * 
    * @param workers mapping
    * @return config
    */
   public TaskNodeConfig setExecutorServiceWorkers(Map<String, Integer> workers) {
      this.executorServiceWorkers = workers;
      return this;
   }

   public Map<String, Integer> getExecutorServiceWorkers() {
      return executorServiceWorkers;
   }

}
