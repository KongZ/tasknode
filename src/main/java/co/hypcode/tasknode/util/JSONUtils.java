/*
 * Licensed to BBTV NEW MEDIA under HYPCODE CO.LTD. license 
 * agreements. See the NOTICE file distributed with this work 
 * for additional information regarding copyright ownership.
 *
 * Unauthorized copying of this file, via any medium is strictly 
 * prohibited proprietary and confidential.
 *
 */

package co.hypcode.tasknode.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * The utilities class that allow to reuse ObjectReader and ObjectWriter instances.
 * 
 * @author Kong
 */
public final class JSONUtils {
   private static ObjectMapper objectMapper = new ObjectMapper();
   private static ObjectWriter objectWriter = objectMapper.writer();
   private static ObjectReader objectReader = objectMapper.reader();

   /**
    * Gets objectWriter.
    * 
    * @return the objectWriter
    */
   public static ObjectWriter getObjectWriter() {
      return objectWriter;
   }

   /**
    * Gets objectReader.
    * 
    * @return the objectReader
    */
   public static ObjectReader getObjectReader() {
      return objectReader;
   }

   /**
    * Gets objectMapper
    * 
    * @return the objectMapper
    */
   public static ObjectMapper getObjectMapper() {
      return objectMapper;
   }

}
