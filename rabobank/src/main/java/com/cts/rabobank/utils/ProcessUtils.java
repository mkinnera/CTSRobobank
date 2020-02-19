package com.cts.rabobank.utils;

import com.cts.rabobank.model.Response;
import com.cts.rabobank.model.StatusBean;
import org.springframework.http.ResponseEntity;

/**
 * ProcessUtils
 */
public final class ProcessUtils {
    private ProcessUtils(){

    }

    public static ResponseEntity<Response> processResponse(Object data, String message, int code) {
        Response response = new Response();
        StatusBean status = new StatusBean();
        status.setCode(code);
        status.setMessage(message);
        response.setStatus(status);
        if (data != null) {
            response.setData(data);
        }
        return Response.responseEntity(response);
    }
    public static ResponseEntity<Response> processResponse(String message, int code) {
        Response response = new Response();
        StatusBean status = new StatusBean();
        status.setCode(code);
        status.setMessage(message);
        response.setStatus(status);
        return Response.responseEntity(response);
    }

}
