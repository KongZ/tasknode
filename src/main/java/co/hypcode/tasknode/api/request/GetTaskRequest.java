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
public class GetTaskRequest {
   private String workerName;
   private String taskId;

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
    * Gets taskId.
    * 
    * @return the taskId
    */
   public String getTaskId() {
      return taskId;
   }

   /**
    * Sets taskId.
    * 
    * @param taskId the taskId to set
    */
   public void setTaskId(String taskId) {
      this.taskId = taskId;
   }

}
