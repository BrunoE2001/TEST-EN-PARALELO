package org.skyline.example.testing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class AppConfig {

//    @Bean
//    public Validator validator(SpringConstraintValidatorFactory constraintValidatorFactory) {
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setConstraintValidatorFactory(constraintValidatorFactory);
//        return validator;
//    }
}
