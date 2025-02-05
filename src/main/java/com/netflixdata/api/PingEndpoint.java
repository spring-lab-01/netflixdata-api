package com.netflixdata.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PingEndpoint {

    @GetMapping("/ping")
    public String sampleData() {
        return "success";
    }

}
