package com._742pm.docs.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocsController {

    @GetMapping("/docs")
    public Doc[] greeting() {
        return new Doc[]{
                new Doc(123, "Default secured doc", new byte[]{100, 127, 103})
        };
    }
}
