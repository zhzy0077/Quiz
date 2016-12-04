package com.nuptsast;

import com.nuptsast.model.User;
import com.nuptsast.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
@SpringBootApplication
public class Application {
  private final Log log = LogFactory.getLog(this.getClass());
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(UserService userService) {
    return (args) -> {
      User user = new User("rootroot", "sastlonglive", "root", "13333333333");
      user.setAuthority("ROLE_ADMIN");
      userService.register(user);
      for (int i = 0; i < 3; i++) {
        String username = Long.toHexString(Double.doubleToLongBits(Math.random()));
        User u = new User(username, username, "计算机部", "13333333333");
        log.info(username);
        userService.register(u);
      }
    };
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