package br.com.senac.ccs.lpc;

import com.fasterxml.jackson.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@JsonInclude (JsonInclude.Include.NON_NULL)
public class Message {


    private String message;
    private User user;
    private String formatdate = "HH:mm:ss";    
    private String time;    

    public Message() {
    }

    public Message( String message, User user ) {
        this.message = message;
        this.user = user;
        Date agora = new Date();
        SimpleDateFormat formata = new SimpleDateFormat(formatdate);
        this.time = formata.format(agora);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}