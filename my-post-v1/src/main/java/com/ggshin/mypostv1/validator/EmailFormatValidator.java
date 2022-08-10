package com.ggshin.mypostv1.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Email;

public class EmailFormatValidator implements ConstraintValidator<EmailFormat, String> {

    @Override
    public void initialize(EmailFormat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("^[\\w\\d]*@[\\w\\d]*\\.(com|net)$");
    }

}
