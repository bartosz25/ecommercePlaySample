package services;

import java.util.List;
import javax.persistence.Query;
import play.db.jpa.JPA;
import models.User;


public class UserService {

	public void addNewUser(User user) {
		// TODO : implement
		
		// TODO : when we'll talking about e-mail sending (if any), we'll add here the code to send "welcome mail"
		
	}
	
	public boolean isUniqueLogin(String login) {
		List<User> users = getByLogin(login);
		return users == null || users.size() == 0;
	}
	
	public List<User> getByLogin(String login) {
		Query query = JPA.em().createQuery("SELECT u FROM User u WHERE u.login = :login");
		query.setParameter("login", login);
		return query.getResultList();
	}
	
}
