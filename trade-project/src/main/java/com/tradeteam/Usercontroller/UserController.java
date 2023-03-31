package com.tradeteam.Usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tradeteam.services.UserService;
import com.tradeteam.entities.User;

@Controller
public class UserController {

	
	@Autowired
	private UserService userService;
	//Mapping methods 
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	 public String reg() {
		 return "register_form"; //name of file to get
	 }
	
	@PostMapping("/registerProcess")
	public String registerNewCohort(
	                                @RequestParam("userName") String userName,
                                    @RequestParam("userPassword") String userPassword,
                                    @RequestParam("userEmail") String userEmail)
	
	{
	
	User u1 = new User( userName,userPassword,userEmail);
	User savedInfo = userService.createUser(u1);
	if (savedInfo != null) {
		return "login";
	}
	else {
		return "failure";
	}
	}
	
	
	
	@PostMapping("/login")
	public ModelAndView login(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword){
	User user = userService.login(userName, userPassword);
	ModelAndView mv = new ModelAndView();
	if (user != null) {
	mv.setViewName("Menupage" );
	} else {
	mv.setViewName("login");
	}
	return mv;
	
	
	
	
	
	
	
	
	}
}
