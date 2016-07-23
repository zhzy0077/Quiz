package com.nuptsast.data;

import com.nuptsast.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDepartment(String department);

    List<Question> findByQuestionContaining(String question);
}
