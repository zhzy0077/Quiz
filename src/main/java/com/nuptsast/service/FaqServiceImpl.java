package com.nuptsast.service;

import com.nuptsast.data.FaqRepository;
import com.nuptsast.model.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zheng on 16/7/30.
 * All Rights Reversed.
 */
@Service
public class FaqServiceImpl implements FaqService {
  private final FaqRepository faqRepository;

  @Autowired
  public FaqServiceImpl(FaqRepository faqRepository) {
    this.faqRepository = faqRepository;
  }

  @Override
  public List<Faq> getFaq() {
    return faqRepository.findAll();
  }
}
