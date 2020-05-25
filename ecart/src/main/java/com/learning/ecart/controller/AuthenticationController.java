package com.learning.ecart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
	
	/**
	 * custom login page for user to login
	 * everyone has access to this page
	 * @return model and view of the login page
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
	
	/**
	 * the home page where everyone lands on to find out
	 * more information about the site and to sign in and 
	 * give new visitor the option to register to be a member
	 * @return model and view of the home page
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
	
	/**
	 * default to the home page and everyone can visit this page
	 * @return model and view of the home page
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView root() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
	
	/**
	 * the hello page is basically the member's page or the dashboard that
	 * registered member lands on when they successfully login  
	 * @return model and view of the member's page
	 */
	@RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
	public ModelAndView hello() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hello"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}

	/**
	 * admin page can only be accessed by registered member 
	 * with "admin" role
	 * @return model and view of the admin user
	 */
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
	
	/**
	 * customized logout page to handle logout for user
	 * @return model and view of the logout page
	 */
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public ModelAndView logout() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logout"); // resources/template/login.html
		System.out.println("modelandview: "+modelAndView);
		return modelAndView;
	}
}
