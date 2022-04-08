package com.hackerearth.hackathonHackerearth.SpringSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {

    @GetMapping("/home")
    public String AccessByALL(){
        return " <h1>welcome all<h1> ";
    }
    @GetMapping("/user")
    public String AccessByUser(){
        return " <h1>welcome User<h1> ";
    }
    @GetMapping("/admin")
    public String AccessByAdmin(){
        return " <h1>welcome Admin<h1> ";
    }
}

