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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kong
 */
public class Node {
   private String id;
   private Map<String, Integer> maxWorker = new HashMap<>();

   /**
    * Gets id.
    * 
    * @return the id
    */
   public String getId() {
      return id;
   }

   /**
    * Sets id.
    * 
    * @param id the id to set
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Gets maxWorker.
    * 
    * @return the maxWorker
    */
   public Map<String, Integer> getMaxWorker() {
      return maxWorker;
   }

   /**
    * Sets maxWorker.
    * 
    * @param maxWorker the maxWorker to set
    */
   public void setMaxWorker(Map<String, Integer> maxWorker) {
      this.maxWorker = maxWorker;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Node other = (Node)obj;
      if (id == null) {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      return true;
   }

}
