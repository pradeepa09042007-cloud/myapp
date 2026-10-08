package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class RegistrationController implements HttpHandler {
    private RegistrationService service;
    private Gson gson = new Gson();

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Minimum CORS configuration
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if ("POST".equals(exchange.getRequestMethod())) {
            try (InputStreamReader reader = new InputStreamReader(exchange.getRequestBody())) {
                User user = gson.fromJson(reader, User.class);
                String result = service.register(user);
                
                int statusCode = "Success".equals(result) ? 201 : 400;
                String jsonResponse = gson.toJson(new ResponseMessage(result));
                
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(statusCode, jsonResponse.getBytes().length);
                
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(jsonResponse.getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(500, -1);
            }
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
    
    // Helper class for JSON responses
    class ResponseMessage {
        String message;
        ResponseMessage(String message) { this.message = message; }
    }
}
