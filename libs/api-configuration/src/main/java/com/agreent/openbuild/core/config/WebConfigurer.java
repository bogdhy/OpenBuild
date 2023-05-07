package com.agreent.openbuild.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.data.web.ReactiveSortHandlerMethodArgumentResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Slf4j
@Configuration
public class WebConfigurer implements WebFluxConfigurer {

  @Bean
  public CorsWebFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) || !CollectionUtils.isEmpty(config.getAllowedOriginPatterns())) {
      log.debug("Registering CORS filter");
      source.registerCorsConfiguration("/api/**", config);
      source.registerCorsConfiguration("/management/**", config);
      source.registerCorsConfiguration("/v3/api-docs", config);
      source.registerCorsConfiguration("/swagger-ui/**", config);
    }
    return new CorsWebFilter(source);
  }

  // TODO: remove when this is supported in spring-boot
  @Bean
  HandlerMethodArgumentResolver reactivePageableHandlerMethodArgumentResolver() {
    return new ReactivePageableHandlerMethodArgumentResolver();
  }

  // TODO: remove when this is supported in spring-boot
  @Bean
  HandlerMethodArgumentResolver reactiveSortHandlerMethodArgumentResolver() {
    return new ReactiveSortHandlerMethodArgumentResolver();
  }
}
