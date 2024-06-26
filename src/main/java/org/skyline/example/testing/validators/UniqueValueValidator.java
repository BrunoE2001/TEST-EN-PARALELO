package org.skyline.example.testing.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.skyline.example.testing.annotations.UniqueValue;
import org.skyline.example.testing.util.IUniqueValueChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {
    @Autowired
    private IUniqueValueChecker uniqueValueChecker;
    private String tableName;
    private String fieldName;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.tableName = constraintAnnotation.tableName();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if (s == null || s.isEmpty()) return true;

        return !this.uniqueValueChecker.existsGameWithTitle(this.tableName, this.fieldName, s);
    }
}
