package com.capella.admin.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HelloController{

    protected final Logger logger = Logger.getLogger(HelloController.class);
    
    @RequestMapping(value={"/secure/index","/"})
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response, Principal principal)
            throws ServletException, IOException {
        logger.info("Returning hello view");
        ModelAndView model = new ModelAndView("index");
        String name = principal.getName();
		model.addObject("username", name);
		model.addObject("message", "Welcome to spring security!!");
		return model;
    }
    
    
    @RequestMapping(value={"/login"})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Returning login view");
        return new ModelAndView("login");
    }

}