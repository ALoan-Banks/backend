package com.revature.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSLTestController {

    @GetMapping(value = "/")
    public String greeting(){
        return "Self Signed SSL is Working && Server is Running";
    }
}