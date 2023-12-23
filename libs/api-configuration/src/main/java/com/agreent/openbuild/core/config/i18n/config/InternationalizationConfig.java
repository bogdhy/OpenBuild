package com.agreent.openbuild.core.config.i18n.config;

import com.agreent.openbuild.core.config.i18n.TranslationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class InternationalizationConfig implements WebFluxConfigurer {

  @Bean
  public TranslationService translationService() {
    return TranslationService.getInstance();
  }
}
