package constraints;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import javax.validation.Constraint;
import javax.validation.Payload;

import constraints.validators.BeforeDateValidator;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = BeforeDateValidator.class)
@play.data.Form.Display(name="constraint.alluppercase")
public @interface BeforeDate {
	String message() default "The date is not before now";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String dateToCompare() default BeforeDateValidator.NOW;
}
