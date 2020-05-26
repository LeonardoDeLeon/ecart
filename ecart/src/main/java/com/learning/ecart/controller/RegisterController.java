package com.learning.ecart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.learning.ecart.domain.User;
import com.learning.ecart.service.UserService;
import com.learning.ecart.validator.UserForm;
import com.learning.ecart.validator.UserValidator;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	 
	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
	   // Form target
	   Object target = dataBinder.getTarget();
	   if (target == null) {
	      return;
	   }
	   System.out.println("Target=" + target);
	 
	   if (target.getClass() == UserForm.class) {
	      dataBinder.setValidator(userValidator);
	   }
	   // ...
	}
	
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		UserForm user = new UserForm();
		modelAndView.addObject("userForm",user);
		modelAndView.setViewName("register"); // resources/template/register.html
		System.out.println("modelAndView: "+modelAndView);
		return modelAndView;
	}
	
   @RequestMapping(value = "/register", method = RequestMethod.POST)
   public String saveRegister(Model model,
		   			@ModelAttribute("userForm") @Validated UserForm userForm,
		   			BindingResult result) throws Exception {
 
      // Validate result of the form
      model.addAttribute("userForm", userForm);
      User newUser = userService.initUserFromForm(userForm);   
      // if form has errors then prompt user to make corrections in the form
      if (result.hasErrors()) return "register";
      
      // if no errors in form, then proceed with adding new user
      userService.addUser(newUser);
      return "registerSuccess";
   }
}
