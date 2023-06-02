package ru.kpfu.itis.utils.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class SamePasswordsValidator implements ConstraintValidator<SamePasswords, Object> {

    private String[] fields;
    private String message;

    @Override
    public void initialize(SamePasswords constraintAnnotation) {
        this.fields = constraintAnnotation.names();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        final BeanWrapperImpl bean = new BeanWrapperImpl(object);
        List<String> fieldValues = new ArrayList<>();

        for (String fieldName : fields) {
            fieldValues.add((String) bean.getPropertyValue(fieldName));
        }

        boolean valid = fieldValues.size() != fieldValues.stream().distinct().count();

        if (!valid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(fields[0]).addConstraintViolation();
        }

        return valid;
    }

}
