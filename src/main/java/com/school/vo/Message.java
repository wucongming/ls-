package com.school.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Message<T> {
    private String msg;
    private int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T content;

    public Message() {
    }

    public Message(String msg, int status, T content) {
        this.msg = msg;
        this.status = status;
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
