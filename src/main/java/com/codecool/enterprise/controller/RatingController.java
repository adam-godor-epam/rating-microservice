package com.codecool.enterprise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RatingController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    //@ResponseBody
    public String renderMain(){
        return "main";
    }
}
