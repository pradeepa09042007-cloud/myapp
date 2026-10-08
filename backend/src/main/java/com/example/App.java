package com.example;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class App {
    
    public String getGreeting() {
        return "Hello, boilerplate..!!";
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting backend server...");

        // Wire dependencies manually (No Spring!)
        UserRepository repo = new JdbcUserRepository();
        RegistrationService service = new RegistrationService(repo);
        RegistrationController controller = new RegistrationController(service);

        // Start built-in Java HTTP Server
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/register", controller);
        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port 8080 without a framework..!");
    }
}
