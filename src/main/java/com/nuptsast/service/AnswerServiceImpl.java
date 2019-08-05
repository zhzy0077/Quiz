package com.nuptsast.service;

import com.nuptsast.data.AnswerRepository;
import com.nuptsast.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zheng on 16/7/27.
 * All Rights Reversed.
 */
@Service
public class AnswerServiceImpl implements AnswerService {
  private final Logger logger = LoggerFactory.getLogger(getClass());
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
      answerRepository.saveAndFlush(answerSaved);
      return answerSaved;
    } else {
      Answer answerInput = new Answer(userId, questionId, answer);
      return answerRepository.saveAndFlush(answerInput);
    }
  }

  @Override
  public Map<Long, String> findAnswerByUserId(Long userId) {
    List<Answer> answers = answerRepository.findByUserId(userId);
    Map<Long, String> finished = new HashMap<>();
    for (Answer answer : answers) {
      finished.put(answer.getQuestionId(), answer.getAnswer());
    }
    logger.info("{}", answers);
    return finished;
  }
}
