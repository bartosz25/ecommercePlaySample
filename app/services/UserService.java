package services;

import java.util.List;
import javax.persistence.Query;

import exception.UserAuthenticationException;
import exception.UserNotFoundException;

import play.Logger;
import play.db.jpa.JPA;
import security.PasswordCreator;
import models.User;


public class UserService {

	public boolean addNewUser(User user) {
		try {
			user.setPassword(PasswordCreator.sha1Password(user.getPassword(), user.getSalt()));
			JPA.em().persist(user);
			JPA.em().flush();
			return true;
		} catch (Exception e) {
			return false;
		}
		// TODO : when we'll talking about e-mail sending (if any), we'll add here the code to send "welcome mail"
	}
	
	public boolean isUniqueLogin(String login) {
		User user = getByLogin(login);
		return user == null;
	}
	
	public User getByLogin(String login) {
		Query query = JPA.em().createQuery("SELECT u FROM User u WHERE u.login = :login");
		query.setParameter("login", login);
		return (User) query.getSingleResult();
	}

	public User getByLoginAndPassword(String login, String password) {
		try {
			User user = getByLogin(login);
			if (user == null) throw new UserNotFoundException("User with login '"+login+"' was not found");
			password = PasswordCreator.sha1Password(password, user.getSalt());
			if (!password.equals(user.getPassword())) throw new UserAuthenticationException("Password for user '"+login+"' doesn't match");
			return user;
		} catch (Exception e) {
			Logger.error("An error occurred on getting user by login and password. Login used: "+login, e);
		}
		return null;
	}
	
}
