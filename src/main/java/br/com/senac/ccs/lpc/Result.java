package br.com.senac.ccs.lpc;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Result {

    private List<Message> messages;
    private String notification;
    private List<User> users;

    public Result() {
    }

    public Result( Message message ) {
        this.messages.add(message);
    }
    
    public Result( String notification, List<User> users ) {
        this.notification = notification;
        this.users = users;
    }
    
    public Result( List<Message> messages, String notification ) {
        this.messages = messages;
        this.notification = notification;
    }

    public Result( List<Message> messages, String notification, List<User> users ) {
        this.messages = messages;
        this.notification = notification;
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification( String notification ) {
        this.notification = notification;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers( List<User> users ) {
        this.users = users;
    }
    
}
