package com.nuptsast.service;

import com.nuptsast.data.QuestionRepository;
import com.nuptsast.model.Choice;
import com.nuptsast.model.Question;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
//        Collections.shuffle(questions);
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

    @Override
    public Boolean importFile(InputStream file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = sheet.getFirstRowNum() + 1;
             i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String department = row.getCell(0).getStringCellValue();
            String questionString = row.getCell(1).getStringCellValue();
            List<Choice> choices = new ArrayList<>();
            for (int j = 2; j < row.getLastCellNum(); j++) {
                String choice = row.getCell(j).getStringCellValue();
                choices.add(new Choice(choice));
            }
            Question question = new Question(questionString, choices, department);
            questionRepository.save(question);
        }
        questionRepository.flush();
        return true;
    }
}
