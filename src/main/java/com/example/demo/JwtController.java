package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
JWT實作範例參考
https://ithelp.ithome.com.tw/articles/10270947
 */
@RestController
public class JwtController {

    @Autowired
    JwtService service;

    @PostMapping("/token")
    public Map<String, String> getToken(@RequestBody Member member) {
        Map<String, String> response = new HashMap<>();
        String token = service.getToken(member);
        response.put("token", token);
        return response;
    }

    @GetMapping("/access")
    public Map<String, String> access() {
        Map<String, String> response = new HashMap<>();
        String message = service.access();
        response.put("message", message);
        return response;
    }
}
