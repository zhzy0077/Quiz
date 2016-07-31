package com.nuptsast.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
@Entity
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String choice;

    public Choice() {
    }

    public Choice(String choice) {
        this.choice = choice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
