package com.example.daohvph35768_ass.DTO;

public class UserDTO {
    private int id;
    private String username;
    private String fullname;
    private String password;
    private String email;

    public UserDTO() {
    }

    public UserDTO( String username, String fullname, String password, String email) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
    }
    public UserDTO(int id, String username, String fullname, String password, String email) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
