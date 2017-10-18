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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Kong
 */
@Path("/")
public class RootApi {
   /**
    * Method handling HTTP GET requests. The returned object will be sent to the client as "text/plain" media
    * type.
    *
    * @return String that will be returned as a text/plain response.
    */
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getRoot() {
      return Response.ok().entity("ok").build();
   }

}
