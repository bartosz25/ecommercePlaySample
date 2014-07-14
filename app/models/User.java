package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal; 

import constraints.BeforeDate;
import constraints.validators.BeforeDateValidator;

import play.Logger;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.Pattern;
import play.data.validation.ValidationError;
import services.ServicesInstances;
import services.UserService;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.*;

@Entity
@Table(name="users")
public class User {

	private int id;

	// TODO : move all hard-coded messages into .properties file as in Spring
    @Required(message = "Login is mandatory field")
    @MaxLength(value = 10, message = "Login can be 10-characters max length")
    @Pattern(value = "[a-z0-9\\-]+", message = "Only alphanumerical lower case characters and - are allowed in this field.")
	private String login;
    @Required(message = "Password is mandatory field")
    @MinLength(value = 10, message = "Password must be at least 10-characters length")
	private String password;
    @BeforeDate(dateToCompare = BeforeDateValidator.NOW, message = "Register date can't be in the past")
	private Date birthday;
    private Date createdTime;

	/**
	 * This is a ad-hoc validation method, invoked only when the "basic" validation (ie. annotation-based) hasn't errors.
	 * 
	 * @return List of ValidationError. Returns null when they're no errors.
	 */
	public List<ValidationError> validate() {
		Logger.debug("Validating user on validate() method");
		UserService userService = (UserService) ServicesInstances.USER_SERVICE.getService();
		boolean isUniqueLogin = userService.isUniqueLogin(this.login);
		if (!isUniqueLogin) {
			List<ValidationError> errors = new ArrayList<ValidationError>();
			errors.add(new ValidationError("login", "This login already exists"));
			return errors;
		}
		return null;
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	public int getId() {
		return this.id;
	}
	@Column(name="login")
	public String getLogin() {
		return this.login;
	}
	@Column(name="passa")
	public String getPassword() {
		return this.password;
	}
	@Temporal(TIMESTAMP)
	@Column(name="created")
	public Date getCreatedTime() {
		if (this.createdTime == null) {
			setCreatedTime(new Date());
		}
		return this.createdTime;
	}
	@Temporal(DATE)
	@Column(name="birthday")
	public Date getBirthday() {
		return this.birthday;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User {id: "+this.id+", login: "+this.login+"}";
	}
	
}
