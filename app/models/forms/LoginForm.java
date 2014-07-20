package models.forms;

import java.util.ArrayList;
import java.util.List;

import models.User;

import play.Logger;
import play.data.validation.ValidationError;
import services.ServicesInstances;
import services.UserService;

public class LoginForm {

	private String login;
	private String password;
	
	public List<ValidationError> validate() {
		UserService userService = (UserService) ServicesInstances.USER_SERVICE.getService();
		User user = userService.getByLoginAndPassword(getLogin(), getPassword());
		if (user == null) {
			List<ValidationError> errors = new ArrayList<ValidationError>();
			errors.add(new ValidationError("login", "Login or password is incorrect"));
			return errors;
		}
		return null;
	}
	
	public String getLogin() {
		return this.login;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginForm {login: "+this.login+"}";
	}
	
}
