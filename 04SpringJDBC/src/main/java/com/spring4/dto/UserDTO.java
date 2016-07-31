
package com.spring4.dto;

public class UserDTO {
    private String userName;
    private String password;
    private String message;

    public boolean messageExists() {
        return message != null && message.trim().length() > 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}