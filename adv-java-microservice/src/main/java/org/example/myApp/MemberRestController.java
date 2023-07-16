package org.example.myApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberRestController {

    @GetMapping
    public String getMembers() {
        System.out.println("hello from get!");
        return "Hello from GET!";
    }

    @PostMapping
    public String createMember() {
        System.out.println("hello from post");
        return "Hello from POST!";
    }
}
