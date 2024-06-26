//package org.skyline.example.testing.config;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorFactory;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SpringConstraintValidatorFactory implements ConstraintValidatorFactory {
//
//    private final AutowireCapableBeanFactory beanFactory;
//
//    public SpringConstraintValidatorFactory(ApplicationContext applicationContext) {
//        this.beanFactory = applicationContext.getAutowireCapableBeanFactory();
//    }
//
//    @Override
//    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
//        return beanFactory.createBean(key);
//    }
//
//    @Override
//    public void releaseInstance(ConstraintValidator<?, ?> instance) {
//        beanFactory.destroyBean(instance);
//    }
//}
