package de.kuweh.oauth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
public class LoginController 
{

	@RequestMapping(value="", method=RequestMethod.GET)
	public String showLogin()
	{
		return "login/loginForm";
	}
}
