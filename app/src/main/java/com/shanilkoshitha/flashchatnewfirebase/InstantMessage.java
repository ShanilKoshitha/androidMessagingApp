package com.shanilkoshitha.flashchatnewfirebase;

public class InstantMessage {

    private String message;
    private String author;

    public InstantMessage(String message, String user) {
        this.message = message;
        this.author = user;
    }

    public InstantMessage() {
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
