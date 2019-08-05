package com.nuptsast.web;

import com.nuptsast.model.User;
import com.nuptsast.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Zheng on 16/7/22.
 * All Rights Reversed.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
  private final UserService userService;
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  public ProfileController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String profile(Model model, HttpSession session) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userService.getUser(username);
    model.addAttribute("user", user);
    session.setAttribute("user", user);
    return "profile";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String updateProfile(User user, Model model, HttpSession session) {
    if (!validate(user.getPhoneNumber(), user.getTargetDepartment())) {
      model.addAttribute("status", Boolean.FALSE);
      user.setUsername(((User) session.getAttribute("user")).getUsername());
      return "profile";
    }
    logger.info("update " + user);
    User originUser = (User) session.getAttribute("user");
    originUser.setPhoneNumber(user.getPhoneNumber());
    originUser.setTargetDepartment(user.getTargetDepartment());
    userService.updateUser(originUser);
    model.addAttribute("status", Boolean.TRUE);
    user.setUsername(((User) session.getAttribute("user")).getUsername());
    return "profile";
  }

  private boolean validate(String phoneNumber, String targetDepartment) {
    if ((phoneNumber == null || phoneNumber.length() != 11)) {
      logger.info("phoneNumber " + phoneNumber + " Wrong");
      return false;
    }
    if (targetDepartment == null) {
      logger.info("department Wrong");
      return false;
    }
    return true;
  }
}
