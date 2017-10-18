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
public class CancelTaskRequest {
   private String workerName;
   private String[] taskIds;

   /**
    * Gets workerName.
    * 
    * @return the workerName
    */
   public String getWorkerName() {
      return workerName;
   }

   /**
    * Sets workerName.
    * 
    * @param workerName the workerName to set
    */
   public void setWorkerName(String workerName) {
      this.workerName = workerName;
   }

   /**
    * Gets taskIds.
    * 
    * @return the taskIds
    */
   public String[] getTaskIds() {
      return taskIds;
   }

   /**
    * Sets taskIds.
    * 
    * @param taskIds the taskIds to set
    */
   public void setTaskIds(String[] taskIds) {
      this.taskIds = taskIds;
   }

}
