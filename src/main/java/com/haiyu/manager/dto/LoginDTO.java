package com.haiyu.manager.dto;



/**
 * @Title: UserDTO
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/21 11:19
 */
public class LoginDTO {
    private String username;

    private String password;

    private String rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe='" + rememberMe + '\'' +
                '}';
    }
}
