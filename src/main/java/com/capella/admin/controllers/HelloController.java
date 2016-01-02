package com.capella.admin.controllers;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
@Controller
public class HelloController{

	protected final Logger logger = Logger.getLogger(HelloController.class);


	@RequestMapping(value={"/secure/index"})
	@PreAuthorize("hasRole('ROLE_USER')")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response,
			Principal principal)
					throws ServletException, IOException {
		ModelAndView model = new ModelAndView("index");
		if(principal != null){
			logger.info("Returning hello view");
			String name = principal.getName();
			model.addObject("username", name);
			model.addObject("message", "Welcome to spring security!!");
		}
		return model;
	}


	@RequestMapping(value={"/login"})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Returning login view");
		return new ModelAndView("login");
	}
	@RequestMapping(value={"/"})
	public String root(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Returning login view");
		return ("redirect:/secure/index");
	}

}