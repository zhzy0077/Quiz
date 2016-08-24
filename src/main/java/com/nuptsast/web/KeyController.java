package com.nuptsast.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Created by Zheng on 16/7/30.
 * All Rights Reversed.
 */
@Controller
@RequestMapping("/key")
public class KeyController {
  private final String KEY = "sast2016";

  @RequestMapping(method = RequestMethod.GET)
  public String getKey() {
    return "key";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String submitKey(@RequestParam String key, HttpSession session) {
    if (Objects.equals(KEY, key)) {
      session.setAttribute("key", Boolean.TRUE);
      return "redirect:/exam";
    } else {
      return "redirect:/key?error";
    }
  }
}
