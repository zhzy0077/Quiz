package com.nuptsast.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
@Controller
@RequestMapping("/")
public class IndexController {
  @RequestMapping(method = RequestMethod.GET)
  public String index() {
    return "index";
  }
}
