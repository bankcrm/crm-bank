package com.axon.bank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.axon.bank.service.AuthService;

@Controller
@RequestMapping("/mybank")
public class AuthController {
	
	@Autowired
	@Qualifier("AuthServiceImpl")
	private AuthService authService;

	@RequestMapping(value="auth", method=RequestMethod.GET)
	public String authUserPage(Model model){
		return "login";
	}
	@RequestMapping(value="homePage", method=RequestMethod.GET)
	public String homePage(Model model, HttpSession session){
		System.out.println("@@@@@@@@__________Home Page");
		return "homePage";
	}
	@RequestMapping(value="sessionTimeoutPage", method=RequestMethod.GET)
	public String sessionTimeoutPage(Model model){
		System.out.println("session invalidated");
		return "redirect:/index.jsp";
	}
	@RequestMapping(value="invalidLogin", method=RequestMethod.GET)
	public String invalidLogin(Model model){
		model.addAttribute("message", "Your login info is not correct");
		return "login";
	}
	
	@RequestMapping(value="denied", method=RequestMethod.GET)
	public String accessDenied(Model model){
		model.addAttribute("message", "You are not allowed.....####");
		return "login";
	}
	@RequestMapping(value="auth-user", method=RequestMethod.POST)
	public String authUser(String username, String password, HttpSession session, Model model){
		String nextPage="homePage";
		String role = authService.authUser(username, password);	
		if(role.length() > 0){
			session.setAttribute("username", username);
			session.setAttribute("role", role);
		}else{
			return "login";
		}
		return nextPage;
	}
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		if(session!=null){
			session.invalidate();
		}
		System.out.println("logout______@@@@@@@@@@@");
		return "redirect:/index.jsp";
	}
}
