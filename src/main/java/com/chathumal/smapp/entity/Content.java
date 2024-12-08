package com.chathumal.smapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "content")
public class Content extends SuperEntity implements Serializable {
    @TableGenerator(name="tblcon", initialValue= 50000)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="tblcon")
    private int cid;
    private String content;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public Content() {
    }

    public Content(int cid, String content, User user) {
        this.cid = cid;
        this.content = content;
        this.user = user;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Content{" +
                "cid=" + cid +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
