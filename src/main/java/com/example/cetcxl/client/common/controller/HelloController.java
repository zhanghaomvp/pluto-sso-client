package com.example.cetcxl.client.common.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class HelloController {
    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName()
                + Arrays.toString(authentication.getAuthorities().toArray());
    }
}
