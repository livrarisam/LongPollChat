package br.com.senac.ccs.lpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class Chat {

    private final ConcurrentHashMap<String, User> usersession;
    private final List<User> users;
    private final Lock lock;
    private final List<Message> messages;

    public Chat() {
        this.usersession = new ConcurrentHashMap<String, User>();
        this.users = new ArrayList<User>();
        this.messages = new ArrayList<Message>();
        this.lock = new ReentrantLock();
    }

    public Result join( String id, String name, Screen screen ) {
            lock.lock();
            Result result = null;
       try {
            User newUser = new User (id, name, screen);
            users.add(newUser);            
            for (User user : usersession.values()) {
                user.notify(new Result(String.format("User %s joined the chat!", newUser.getName()), users));
            }

            result = new Result(String.format("Welcome %s!", newUser.getName()), users);
            usersession.put(id, newUser);            
        }
        finally {
            lock.unlock();
       }
       return result;
    }

    public void sendchat( String id, String message ) {
            lock.lock();
       try {
            User sender = usersession.get(id);
            Message usermessage = new Message(message, sender);
            messages.add(usermessage);
            if (messages.size() > 16) {
                messages.remove(0);
            }
            
            for (User user : usersession.values()) {
                user.notify(new Result(messages, String.format("User %s send a message!", sender.getName()), users));
            }            
            
        }
        finally {
            lock.unlock();
       }
    }

    public void bind( String id, Screen screen ) {
        User user = usersession.get(id);
        user.setScreen(screen);
    }

    @PostConstruct
    public void init() {
        //Nothing Yet
    }
}
