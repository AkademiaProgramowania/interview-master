package pl.pop.interview.master.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @GetMapping("api/debug")
    public String getDebugMessage() {
        return "Hello";
    }
}