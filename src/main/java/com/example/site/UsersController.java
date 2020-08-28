package com.example.site;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @GetMapping("/greeting")
public List<User> greeting(){
        return ParserUserPages.users;
    }
    @GetMapping("home")
    public String home(){
        return "index";
    }
}