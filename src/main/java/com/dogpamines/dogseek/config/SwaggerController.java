package com.dogpamines.dogseek.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
    @GetMapping("/api/doc/")
    public String redirectSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}
