package org.daewon.phreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/map")
public class mapController {

    @GetMapping("/categoryResult")
    public void categoryResult() {

    }

    @GetMapping("/nearResult")
    public void nearResult()     {

    }

    @GetMapping("/searchResult")
    public void searchResult() {

    }

}
