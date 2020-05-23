package com.learning.ecart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView root() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
	public ModelAndView hello() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logout"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
}
