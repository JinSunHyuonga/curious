package com.jin.curious;

public class UserDTO {
    private String email;
    private String channel;

    public UserDTO(){
    }

    public UserDTO(String email, String channel){
        this.email = email;
        this.channel = channel;
    }

    public String getEmail(){
        return email;
    }

    public String getChannel(){
        return channel;
    }
}
