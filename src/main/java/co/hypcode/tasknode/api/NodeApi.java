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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.hypcode.tasknode.Node;
import co.hypcode.tasknode.SystemConfig;
import co.hypcode.tasknode.api.request.SetWorkerRequest;
import co.hypcode.tasknode.api.response.AcknowledgeResponse;
import co.hypcode.tasknode.api.response.NodeResponse;
import co.hypcode.tasknode.util.JSONUtils;

/**
 * @author Kong
 */
@Path("/nodes")
public class NodeApi {
   private Logger logger = LoggerFactory.getLogger(NodeApi.class);

   @Inject
   private RedissonClient redisson;

   /**
    * Method handling HTTP GET requests. The returned object will be sent to the client as "text/plain" media
    * type.
    *
    * @return String that will be returned as a text/plain response.
    * @throws JsonProcessingException
    */
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getNodes() throws JsonProcessingException {
      final List<Node> nodeList = new ArrayList<>();
      try {
         RSet<Node> rSet = redisson.getSet(SystemConfig.KEY_NODE_LIST);
         rSet.forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
               nodeList.add(node);
            }
         });
      }
      catch (Exception e) {
         logger.error(e.getMessage());
      }
      NodeResponse nodeResponse = new NodeResponse();
      nodeResponse.setNodes(nodeList);
      return Response.ok().entity(JSONUtils.getObjectWriter().writeValueAsString(nodeResponse)).build();
   }

   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   public Response setWorker(SetWorkerRequest workerRequest) {
      if (workerRequest == null)
         return Response.status(Response.Status.BAD_REQUEST).build();
      redisson.getExecutorService(workerRequest.getName()).registerWorkers(workerRequest.getMaxWorker());
      return Response.ok().entity(AcknowledgeResponse.ACK()).build();
   }

}
