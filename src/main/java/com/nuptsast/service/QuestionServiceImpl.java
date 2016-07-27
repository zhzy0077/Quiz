package com.nuptsast.service;

import com.nuptsast.data.QuestionRepository;
import com.nuptsast.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Zheng on 16/7/22.
 * All Rights Reversed.
 */

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getQuestions(String department) {
        List<Question> questions = questionRepository.findByDepartment(department);
        for (Question question : questions) {
            Collections.shuffle(question.getChoices());
        }
        Collections.shuffle(questions);
        return questions;
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.saveAndFlush(question);
    }

    @Override
    public void removeQuestion(Long id) {
        questionRepository.delete(id);
    }

    @Override
    public List<Question> findQuestionContaining(String question) {
        return questionRepository.findByQuestionContaining(question);
    }
}
