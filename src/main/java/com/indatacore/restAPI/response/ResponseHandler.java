package com.indatacore.restAPI.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message , HttpStatus status, Object responseObject){

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("message",message);

        map.put("status",status.value());

        map.put("data",responseObject);

        return new ResponseEntity<Object>(map,status);

    }
}
