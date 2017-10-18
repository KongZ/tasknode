/*
 * Licensed to BBTV NEW MEDIA under HYPCODE CO.LTD. license 
 * agreements. See the NOTICE file distributed with this work 
 * for additional information regarding copyright ownership.
 *
 * Unauthorized copying of this file, via any medium is strictly 
 * prohibited proprietary and confidential.
 *
 */

package co.hypcode.tasknode.api.response;

import java.util.List;

import co.hypcode.tasknode.Node;

/**
 * @author Kong
 */
public class NodeResponse {
   private List<Node> nodes;

   /**
    * Gets nodes.
    * @return the nodes
    */
   public List<Node> getNodes() {
      return nodes;
   }

   /**
    * Sets nodes.
    * @param nodes the nodes to set
    */
   public void setNodes(List<Node> nodes) {
      this.nodes = nodes;
   }
   
   
}
