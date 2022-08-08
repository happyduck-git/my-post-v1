package com.ggshin.mypostv1.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailFormatValidator.class})
public @interface EmailFormat {
    String message() default "올바른 형식의 이메일이 아닙니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
