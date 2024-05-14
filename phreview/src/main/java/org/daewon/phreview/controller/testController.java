package org.daewon.phreview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
public class testController {

    @GetMapping("/home")
    public void home() {

    }

    @GetMapping("/category")
    public void category() {

    }

    @GetMapping("/pharmacyInfo")
    public void pharmacyInfo() {

    }

}


