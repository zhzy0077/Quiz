package com.nuptsast.service;

import com.nuptsast.model.Answer;

/**
 * Created by Zheng on 16/7/27.
 * All Rights Reversed.
 */
public interface AnswerService {
    Answer saveAnswer(Long userId, Long questionId, String answer);
}
