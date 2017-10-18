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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kong
 */
public class AcknowledgeResponse {
   private static Map<String, Boolean> ACK = new HashMap<>();

   static {
      ACK.put("acknowledge", true);
   }

   public static Map<String, Boolean> ACK() {
      return ACK;
   }
}
