package com.nuptsast.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zheng on 16/7/26.
 * All Rights Reversed.
 */
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Map<Long, String> answer = new HashMap<>();

    public Answer(Map<Long, String> answer) {
        this.answer = answer;
    }

    Answer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, String> getAnswer() {
        return answer;
    }

    public void setAnswer(Map<Long, String> answer) {
        this.answer = answer;
    }
}
