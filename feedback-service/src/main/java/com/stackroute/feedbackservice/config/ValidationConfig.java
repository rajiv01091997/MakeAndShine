package com.stackroute.feedbackservice.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import javax.validation.Validator;
@Configuration
public class ValidationConfig {
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(Validator validator){
        return new ValidatingMongoEventListener(validator);
    }
}