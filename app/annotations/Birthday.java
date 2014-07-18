package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD,METHOD})
@Retention(RUNTIME)
//@play.data.Form.Display(name = "format.datetime", attributes = { "pattern" })
public @interface Birthday {

	String format() default "dd/MM/yyyy";
	
}
