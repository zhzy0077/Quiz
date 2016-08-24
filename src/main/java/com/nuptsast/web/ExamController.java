package com.nuptsast.web;

import com.nuptsast.model.Question;
import com.nuptsast.model.User;
import com.nuptsast.service.AnswerService;
import com.nuptsast.service.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Zheng on 16/7/22.
 * All Rights Reversed.
 */
@Controller
public class ExamController {
  private final QuestionService questionService;
  private final AnswerService answerService;
  private Logger logger = Logger.getLogger(getClass());

  @Autowired
  public ExamController(QuestionService questionService, AnswerService answerService) {
    this.questionService = questionService;
    this.answerService = answerService;
  }

  @RequestMapping(value = "/exam", method = RequestMethod.GET)
  public String selectQuestion(HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (session.getAttribute("questions") == null) {
      logger.info(user);
      session.setAttribute("questions", questionService.getQuestions(user.getTargetDepartment()));
      session.setAttribute("finished", answerService.findAnswerByUserId(user.getId()));
    }
    return "redirect:/exam/0";
  }

  @RequestMapping(value = "/exam/{questionId}", method = RequestMethod.GET)
  public String showQuestion(@PathVariable Integer questionId, Model model, HttpSession session) {
    if (session.getAttribute("key") == null) {
      return "redirect:/key";
    }
    Object questions = session.getAttribute("questions");
    if (questions == null) {
      return "redirect:/exam";
    }
    List questionList = (List) questions;
    model.addAttribute("question", questionList.get(questionId));
    model.addAttribute("questionCount", questionList.size());
    model.addAttribute("questionId", questionId);
    return "exam";
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/exam/{questionId}", method = RequestMethod.POST)
  public String submitQuestion(@PathVariable Long questionId,
                               @RequestParam("answer") String answer,
                               @RequestParam("submit") String direct,
                               HttpSession session) {
    User user = (User) session.getAttribute("user");
    List<Question> questions = (List<Question>) session.getAttribute("questions");
    Map<Long, String> finished = (Map<Long, String>) session.getAttribute("finished");
    answerService.saveAnswer(user.getId(), questions.get(questionId.intValue()).getId(), answer);
    finished.put(questions.get(questionId.intValue()).getId(), answer);
    if (Objects.equals(direct, "next")) {
      return "redirect:/exam/" + (questionId + 1);
    } else if (Objects.equals(direct, "prev")) {
      return "redirect:/exam/" + (questionId - 1);
    } else {
      return "redirect:/profile";
    }
  }
}
