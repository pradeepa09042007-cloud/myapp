package com.example;

public class App {
    
    public String getGreeting() {
        return "Hello, boilerplate..!!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        System.out.println("Backend is running without a framework!");
    }
}
