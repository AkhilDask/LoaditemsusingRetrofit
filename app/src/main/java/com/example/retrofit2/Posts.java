package com.example.retrofit2;

public class Posts {
     String title;
     String body;

    public Posts(String title,String body){
        this.title = title;
        this.body = body;
    }
    public String getTitle() {

        return title;
    }
    public String getBody() {

        return body;
    }
}
