package ua.goryainov.hibernate.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administration")
public class Administration {	
	private String login;	
	private String password;

	public Administration() {
	}
	public Administration(String login, String password) {
		this.login = login;
		this.password = password;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login", unique = true, nullable = false)
	public String getLogin() {
		return login;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Administration [login=" + login + ", password=" + password + "]";
	}
}