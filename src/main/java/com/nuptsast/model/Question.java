package com.nuptsast.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
@Entity
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  private String question;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Choice> choices;
  @NotNull
  private String department;


  public Question(String question, List<Choice> choices, String department) {
    this.question = question;
    this.choices = choices;
    this.department = department;
  }

  public Question() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public List<Choice> getChoices() {
    return choices;
  }

  public void setChoices(List<Choice> choices) {
    this.choices = choices;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
