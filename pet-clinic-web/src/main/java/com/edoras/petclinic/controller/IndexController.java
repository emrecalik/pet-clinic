package com.edoras.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String showIndexPage() {
        return "index";
    }

    @RequestMapping("/oups")
    public String showErrorPage() {
        return "notImplemented";
    }
}
