package com.nuptsast.service;

import com.nuptsast.data.AnswerRepository;
import com.nuptsast.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Zheng on 16/7/27.
 * All Rights Reversed.
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer saveAnswer(Long userId, Long questionId, String answer) {
        Answer answerSaved = answerRepository.findOneByUserIdAndQuestionId(userId, questionId);
        if (answerSaved != null) {
            answerSaved.setAnswer(answer);
            answerRepository.flush();
            return answerSaved;
        } else {
            Answer answerInput = new Answer(userId, questionId, answer);
            return answerRepository.saveAndFlush(answerInput);
        }
    }
}
