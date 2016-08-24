package com.nuptsast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.stereotype.Component;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

@Component
class ServletCustomizer implements EmbeddedServletContainerCustomizer {
  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
    mappings.add("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    container.setMimeMappings(mappings);
  }
}