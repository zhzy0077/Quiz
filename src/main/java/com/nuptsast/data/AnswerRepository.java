package com.nuptsast.data;

import com.nuptsast.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zheng on 16/7/27.
 * All Rights Reversed.
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findOneByUserIdAndQuestionId(Long userId, Long questionId);
}
