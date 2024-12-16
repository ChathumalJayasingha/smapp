package com.chathumal.smapp.entity;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "follow")
public class Follow extends SuperEntity implements Serializable {
    @TableGenerator(name="tblflw", initialValue= 70000)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="tblflw")
    private int fid;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",unique = false, nullable = false)
    private User user;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "flwr_id", unique = false, nullable = false)
    private User flwusr;

    public Follow() {
    }

    public Follow(int fid, User user, User flwusr) {
        this.fid = fid;
        this.user = user;
        this.flwusr = flwusr;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFlwusr() {
        return flwusr;
    }

    public void setFlwusr(User flwusr) {
        this.flwusr = flwusr;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "fid=" + fid +
                ", user=" + user +
                ", flwusr=" + flwusr +
                '}';
    }
}
