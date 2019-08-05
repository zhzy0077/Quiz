package com.nuptsast.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nuptsast.model.Question;
import com.nuptsast.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Zheng on 16/7/23.
 * All Rights Reversed.
 */
@Controller
@RequestMapping()
public class ManageController {
  private final QuestionService questionService;
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  public ManageController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @RequestMapping(value = "/manage", method = RequestMethod.GET)
  public String manage(Model model) {
    model.addAttribute("question", new Question());
    return "manage";
  }

  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public String searchQuestion(@RequestParam("question") String question, Model model) {
    List<Question> questions = questionService.findQuestionContaining(question);
    model.addAttribute("questions", questions);
    return "manage";
  }

  @RequestMapping(value = "/import", method = RequestMethod.POST)
  public
  @ResponseBody
  ObjectNode importQuestion(@RequestBody Question question) {
    logger.info("question to be imported is " + question);
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode node = mapper.createObjectNode();
    questionService.addQuestion(question);
    node.put("status", true);
    return node;
  }

  @RequestMapping(value = "/importFile", method = RequestMethod.POST)
  public String uploadFile(
    @RequestParam("file") MultipartFile file) {
    logger.info(file.getContentType() + " " + file.getOriginalFilename() + " " + file.getSize());
    try {
      questionService.importFile(file.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
      return "redirect:/manage?error";
    }
    return "redirect:/manage";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String deleteQuestion(@RequestParam("id") Long id) {
    questionService.removeQuestion(id);
    return "manage";
  }
}
