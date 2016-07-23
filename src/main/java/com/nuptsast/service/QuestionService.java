package com.nuptsast.service;

import com.nuptsast.model.Question;

import java.util.List;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */

public interface QuestionService {
    Question addQuestion(Question question);

    List<Question> getQuestions(String department);

    void removeQuestion(Long id);

    List<Question> findQuestionContaining(String question);
}
