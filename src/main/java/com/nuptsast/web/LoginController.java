package com.nuptsast.web;

import com.nuptsast.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private Logger logger = Logger.getLogger(getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model, HttpSession session) {
//        logger.info("sesson has " + session.getAttributeNames() + session.getAttribute("lastUsername"));
        if (session.getAttribute("lastUsername") != null)
            model.addAttribute("user", new User((String) session.getAttribute("lastUsername"), null));
        else
            model.addAttribute("user", new User());
        return "login";
    }
}
