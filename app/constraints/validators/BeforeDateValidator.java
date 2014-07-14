package constraints.validators;

import java.util.Date;

import javax.validation.ConstraintValidator;

import constraints.BeforeDate;

import play.Logger;
import play.data.validation.Constraints;
import play.libs.F.Tuple;

/**
 * Validator used to check if field's date occurs after the date specified in dateToCompare parameter of @BeforeDate constraint.
 * 
 * @author bartosz
 *
 */
public class BeforeDateValidator extends Constraints.Validator<Date> implements ConstraintValidator<BeforeDate, Date>  {

	public static final String NOW = "now";
	private Date dateToCompare;
	
	@Override
	public void initialize(BeforeDate annot) {
		if (annot.dateToCompare().equals(NOW)) {
			dateToCompare = new Date();
		}
	}

	@Override
	public Tuple<String, Object[]> getErrorMessageKey() {
		// TODO : resolve generics warning
		return new Tuple("beforeDate", new Object() {});
	}

	@Override
	public boolean isValid(Date toValid) {
		Logger.debug("Validating "+toValid);
		return toValid != null && toValid.before(dateToCompare);
	}

}
