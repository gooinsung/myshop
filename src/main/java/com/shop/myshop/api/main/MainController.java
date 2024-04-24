package com.shop.myshop.api.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    @GetMapping
    public String mainPage(){
        return "index";
    }
}
