package com.nuptsast.web;

import com.nuptsast.model.Question;
import com.nuptsast.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Zheng on 16/7/22.
 * All Rights Reversed.
 */
@Controller
public class ExamController {
    private final QuestionService questionService;

    @Autowired
    public ExamController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/exam", method = RequestMethod.GET)
    public String selectQuestion(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (session.getAttribute("questions") == null) {
            session.setAttribute("questions", questionService.getQuestions(username));
        }
        return "redirect:/exam/0";
    }

    @RequestMapping(value = "/exam/{questionId}", method = RequestMethod.GET)
    public String showQuestion(@PathVariable Integer questionId, Model model, HttpSession session) {
        Object questions = session.getAttribute("questions");
        if (questions == null) {
            return "redirect:/exam";
        }
        @SuppressWarnings("unchecked")
        List<Question> questionList = (List<Question>) questions;
        model.addAttribute("question", questionList.get(questionId));
        model.addAttribute("questionCount", questionList.size());
        return "exam";
    }
}
