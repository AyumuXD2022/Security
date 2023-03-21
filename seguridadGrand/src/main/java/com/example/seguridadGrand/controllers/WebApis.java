package com.example.seguridadGrand.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebApis {

    @GetMapping("/usuario")
    public ResponseEntity<?>usuario(){
        return ResponseEntity.ok("Hello usuario y admin");
    }

    @GetMapping("/admin")
    public ResponseEntity<?>admin(){
        return ResponseEntity.ok("Hello admin");
    }
}
