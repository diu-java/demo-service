package com.diu.edu.demoservice.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DemoValidator.class)
public @interface DemoValidation {
    public String message() default "Demo validation";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
