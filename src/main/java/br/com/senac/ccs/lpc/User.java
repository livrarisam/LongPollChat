package br.com.senac.ccs.lpc;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

    private String id;
    private String name;
    @JsonIgnore
    private Screen screen;

    public User() {
        
    }

    public User( String id, String name ) {
        this();
        this.id = id;
        this.name = name;
    }

    public User( String id, String name, Screen screen ) {
        this( id, name );
        this.screen = screen;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScreen( Screen screen ) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }
    
    public void notify( Result result ) {
        if(screen != null) {
            screen.show(result);
        }
    }
}
