package com.diu.edu.demoservice.annotations;



import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoValidator implements ConstraintValidator<DemoValidation, String> {


    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Step 1 Item info DAO");
        System.out.println(name);

        if(name.equals("hello")){
            return true;
        }else{
            return false;
        }
    }
}
