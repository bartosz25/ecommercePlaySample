package controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import models.User;
import play.*;
import play.data.Form;
import play.data.format.Formatters;
import play.data.format.Formatters.SimpleFormatter;
import play.mvc.*;
import play.db.jpa.Transactional;
import services.ServicesInstances;
import services.UserService;

/**
 * To work with new view, we need to import it as a normal Java dependency.
 */
import views.html.UserController.*;

public class UserController extends Controller {

	/**
	 * This Form object is common for all controllers. It's a empty version of register form. We create the version of form
	 * handled by submit method (registerSubmit) with userForm.bindFromRequest().
	 */
	private static Form<User> userForm = Form.form(User.class);
	
	@Transactional(readOnly=true)
    public static Result register() {
        return ok(register.render(userForm));
    }

	@Transactional()
    public static Result registerSubmit() {
    	Form<User> submittedForm = userForm.bindFromRequest("login", "password", "birthday");
    	Logger.debug("Data is :"+submittedForm.data());
    	Logger.debug("User errors :"+submittedForm.errors());
    	Logger.debug("User global errors :"+submittedForm.globalErrors());
    	Logger.debug("User hasGlobalErrors :"+submittedForm.hasGlobalErrors());
    	Logger.debug("User hasErrors :"+submittedForm.hasErrors());
    	if (!submittedForm.hasErrors()) {
        	User user = submittedForm.get();
        	Logger.debug("User from form is:"+user);
        	UserService userService = (UserService) ServicesInstances.USER_SERVICE.getService();
        	boolean added = userService.addNewUser(user);
        	if (added) {
        		// TODO : redirect to success page
        	} else {
        		// TODO : pass error message to the template
        	}
        	Logger.debug("Found user :"+user);
    	}
    	return ok(register.render(submittedForm));
    }
}
