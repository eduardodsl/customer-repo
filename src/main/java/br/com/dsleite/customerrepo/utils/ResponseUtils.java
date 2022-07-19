package br.com.dsleite.customerrepo.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {
    
    public static Map<String, Object> defaultResponse(Object data, String message, boolean success){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", data);
        map.put("message", message);
        map.put("success", success);
        return map;
    }

}
