/*
 * Licensed to BBTV NEW MEDIA under HYPCODE CO.LTD. license 
 * agreements. See the NOTICE file distributed with this work 
 * for additional information regarding copyright ownership.
 *
 * Unauthorized copying of this file, via any medium is strictly 
 * prohibited proprietary and confidential.
 *
 */

package co.hypcode.tasknode.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.hypcode.tasknode.SystemConfig;
import co.hypcode.tasknode.api.request.CancelTaskRequest;
import co.hypcode.tasknode.api.request.GetTaskRequest;
import co.hypcode.tasknode.api.response.AcknowledgeResponse;

/**
 * @author Kong
 */
@Path("/tasks")
public class TaskApi {
   private Logger logger = LoggerFactory.getLogger(TaskApi.class);

   @Inject
   private RedissonClient redisson;

   @GET
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response getTask(GetTaskRequest getTaskRequest) throws JsonProcessingException {
      return Response.ok().entity(AcknowledgeResponse.ACK()).build();
   }

   @DELETE
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteTask(CancelTaskRequest cancelTaskRequest) throws JsonProcessingException {
      String[] taskIds = cancelTaskRequest.getTaskIds();
      if (taskIds != null) {
         String workerName = cancelTaskRequest.getWorkerName();
         if (workerName == null)
            workerName = SystemConfig.DEFAULT_WORKER_NAME;
         RScheduledExecutorService executorService = redisson.getExecutorService(workerName);
         for (String taskId : taskIds) {
            executorService.cancelTask(taskId);
         }
      }
      return Response.ok().entity(AcknowledgeResponse.ACK()).build();
   }

}
