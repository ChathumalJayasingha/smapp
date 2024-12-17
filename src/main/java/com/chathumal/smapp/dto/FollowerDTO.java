package com.chathumal.smapp.dto;

public class FollowerDTO {
    private String fid;
    private String flwr_id;
    private String user_id;
    private String cid;
    private String content;

    public FollowerDTO(String fid, String flwr_id, String user_id, String cid, String content) {
        this.fid = fid;
        this.flwr_id = flwr_id;
        this.user_id = user_id;
        this.cid = cid;
        this.content = content;
    }
    public FollowerDTO(){}

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFlwr_id() {
        return flwr_id;
    }

    public void setFlwr_id(String flwr_id) {
        this.flwr_id = flwr_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
