package ru.kpfu.itis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
@ComponentScan("ru.kpfu.itis.services")
@EnableJpaRepositories(basePackages = "ru.kpfu.itis.repositories")
public class RootConfig {
}
