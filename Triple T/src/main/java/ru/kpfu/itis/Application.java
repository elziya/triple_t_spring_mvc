package ru.kpfu.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kpfu.itis.config.*;

@Configuration
@EnableAutoConfiguration
@Import({RootConfig.class, WebConfig.class, OAuthSecurityConfiguration.class, WebSecurityConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}
