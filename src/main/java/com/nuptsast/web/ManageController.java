package com.nuptsast.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nuptsast.model.Question;
import com.nuptsast.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public ManageController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage() {
        return "manage";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Question> searchQuestion(@RequestParam("question") String question) {
        return questionService.findQuestionContaining(question);
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public
    @ResponseBody
    ObjectNode importQuestion(@RequestBody String json) {
        System.out.println(json);
//        questionService.addQuestion(question);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Question question = mapper.readValue(json, Question.class);
            questionService.addQuestion(question);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectNode node = mapper.createObjectNode();
        node.put("status", true);
        return node;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ObjectNode deleteQuestion(@RequestParam("id") Long id) {
        questionService.removeQuestion(id);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("status", true);
        return node;
    }
}
