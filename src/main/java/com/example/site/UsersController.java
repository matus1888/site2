package com.example.site;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
public List<User> greeting(){
        return ParserUserPages.users;
    }
}