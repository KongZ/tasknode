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

import java.net.URI;

/**
 * @author Kong
 */
public class ConsoleConfig {
   private URI bindAddress;

   /**
    * Gets bindAddress.
    * 
    * @return the bindAddress
    */
   public URI getBindAddress() {
      return bindAddress;
   }

   /**
    * Sets bindAddress.
    * 
    * @param bindAddress the bindAddress to set
    */
   public void setBindAddress(URI bindAddress) {
      this.bindAddress = bindAddress;
   }

}
