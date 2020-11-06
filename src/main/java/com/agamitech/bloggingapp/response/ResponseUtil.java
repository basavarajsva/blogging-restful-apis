package com.agamitech.bloggingapp.response;

import java.util.Date;

public class ResponseUtil {

    public static Response getResponse(String status, int id) {
        return new Response(status, id, new Date());
    }

    public static Response getResponse(String status) {
        return new Response(status, 0, new Date());
    }
}
