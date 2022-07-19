package br.com.dsleite.customerrepo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {
    
    public static Map<String, Object> successfullResponse(String message, Object data){
        Map<String, Object> map = baseResponse(message, true);
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> failedResponse(String message, List<String> invalidFields){
        Map<String, Object> map = baseResponse(message, false);
        map.put("empty", invalidFields);
        return map;
    }

    public static Map<String, Object> failedResponse(String message, String invalidField){
        Map<String, Object> map = baseResponse(message, false);
        List<String> invalidFields = new ArrayList<>();
        invalidFields.add(invalidField);
        map.put("empty", invalidFields);
        return map;
    }

    public static Map<String, Object> failedResponse(String message){
        return baseResponse(message, false);
    }

    public static Map<String, Object> baseResponse(String message, boolean success){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("success", success);
        return map;
    }

}
