package com.nuptsast.web;

import com.nuptsast.model.User;
import com.nuptsast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Zheng on 16/7/22.
 * All Rights Reversed.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
  private UserService userService;

  @Autowired
  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String register(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String processRegister(@Valid User user, Errors errors, HttpServletRequest request) throws ServletException {
    if (errors.hasErrors()) {
//            System.out.println(errors.getAllErrors());
      return "register";
    }
    userService.register(user);
    request.login(user.getUsername(), user.getPassword());
    return "redirect:/profile";
  }


}
