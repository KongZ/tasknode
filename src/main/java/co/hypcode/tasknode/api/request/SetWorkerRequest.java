/*
 * Licensed to BBTV NEW MEDIA under HYPCODE CO.LTD. license 
 * agreements. See the NOTICE file distributed with this work 
 * for additional information regarding copyright ownership.
 *
 * Unauthorized copying of this file, via any medium is strictly 
 * prohibited proprietary and confidential.
 *
 */

package co.hypcode.tasknode.api.request;

/**
 * @author Kong
 */
public class SetWorkerRequest {
   private String name;
   private int maxWorker;

   /**
    * Gets name.
    * 
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * Sets name.
    * 
    * @param name the name to set
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * Gets maxWorker.
    * 
    * @return the maxWorker
    */
   public int getMaxWorker() {
      return maxWorker;
   }

   /**
    * Sets maxWorker.
    * 
    * @param maxWorker the maxWorker to set
    */
   public void setMaxWorker(int maxWorker) {
      this.maxWorker = maxWorker;
   }

}
