package org.skyline.example.testing.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.skyline.example.testing.validators.UniqueValueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValueValidator.class)
@Documented
public @interface UniqueValue {
    String message() default "{org.hibernate.validator.UniqueValue.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldName();
    String tableName();

    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        UniqueValue[] value();
    }
}
