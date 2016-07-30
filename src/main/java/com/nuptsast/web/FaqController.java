package com.nuptsast.web;

import com.nuptsast.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Zheng on 16/7/30.
 * All Rights Reversed.
 */
@Controller
@RequestMapping("/faq")
public class FaqController {
    private final FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showFaq(Model model) {
        model.addAttribute("faqs", faqService.getFaq());
        return "faq";
    }
}
