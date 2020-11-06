package com.agamitech.bloggingapp.response;

import java.util.Date;

public class Response {

    private String status;

    private Date timeStamp;

    private int id;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Response(String status, int id, Date date) {
        this.status = status;
        this.id = id;
        this.timeStamp=date;
    }

    public String getStatus() {
        return status;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getId() {
        return id;
    }
}
