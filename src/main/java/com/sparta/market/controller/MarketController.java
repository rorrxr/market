package com.sparta.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketController {
    @GetMapping("/")
    public String view() {
        return "view";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/")
    public String edit() {
        return "edit";
    }

}
