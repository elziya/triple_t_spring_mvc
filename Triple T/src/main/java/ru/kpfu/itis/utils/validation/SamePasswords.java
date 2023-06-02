package ru.kpfu.itis.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SamePasswordsValidator.class)
public @interface SamePasswords {

    String message() default "The passwords you entered do not match";

    String[] names() default {};

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
