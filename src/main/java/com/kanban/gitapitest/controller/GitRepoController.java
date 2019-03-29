package com.kanban.gitapitest.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitRepoController {


    @GetMapping("/")
    public String main(){
        return "git main";
    }


}
