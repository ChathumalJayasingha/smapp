package com.chathumal.smapp.dto;

public class FollowingAndUnfollowingDTO {
    private int id;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String password;
    private boolean fulacs;
    private String following;

    public FollowingAndUnfollowingDTO(int id, String name, String address, String contact, String email, String password, boolean fulacs, String following) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.fulacs = fulacs;
        this.following = following;
    }

    public FollowingAndUnfollowingDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFulacs() {
        return fulacs;
    }

    public void setFulacs(boolean fulacs) {
        this.fulacs = fulacs;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
}
