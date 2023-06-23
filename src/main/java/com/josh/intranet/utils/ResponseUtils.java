package com.josh.intranet.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

  public static Map<String,String> createSuccessResponse(String message){
    Map<String,String> response = new HashMap<>();
    response.put("message", message);
    return response;
  }
}
