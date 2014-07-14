package controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import models.User;
import play.*;
import play.data.Form;
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
		// TODO : register formatter for java.util.Date and try to make register validator works with new birthday field
    	Form<User> submittedForm = userForm.bindFromRequest();
    	Logger.debug("Data is :"+submittedForm.data());
    	Logger.debug("User errors :"+submittedForm.errors());
    	Logger.debug("User global errors :"+submittedForm.globalErrors());
    	Logger.debug("User hasGlobalErrors :"+submittedForm.hasGlobalErrors());
    	Logger.debug("User hasErrors :"+submittedForm.hasErrors());
    	if (!submittedForm.hasErrors()) {
        	User user = submittedForm.get();
        	UserService userService = (UserService) ServicesInstances.USER_SERVICE.getService();
        	userService.addNewUser(user);
        	Logger.debug("Found user :"+user);
    	} else {
    		
    	}
    	return ok(register.render(submittedForm));
//    	return redirect(controllers.routes.UserController.register());
    }
}
