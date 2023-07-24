package com.gzw;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorldController {

    @RequestMapping("/world")
    public String handle(){
        return "helloworld";

    }
}
