package ua.goryainov.hibernate.model;


import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	private int userId;	
	private String login;	
	private String password;	
	private String name;	
	private String EDRPOU;	
	private String mail;
	private String tel1;
	private String tel2;
	private String deliveryAdress;
	private long rating;
	private Set<Action> actionList = new HashSet<Action>();
	private Set<Commission> orderList = new HashSet<Commission>();
    
	public User() {
		this.login = "";
		this.password = "";
		this.name = "";
		this.EDRPOU = "";
		this.mail = "";
		this.tel1 = "";
		this.tel2 = "";
		this.deliveryAdress = "";
		this.rating = 0;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	public User(String login, String password,String name,
				String EDRPOU, String mail, String tel1, 
				String tel2, String deliveryAdress, long rating) {
		this(login, password);
		this.name = name;
		this.EDRPOU = EDRPOU;
		this.mail = mail;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.deliveryAdress = deliveryAdress;
		this.rating = rating;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	public int getUserId() {
		return userId;
	}
	@Column(name = "login")
	public String getLogin() {
		return login;
	}	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "EDRPOU")
	public String getEDRPOU() {
		return EDRPOU;
	}
	@Column(name = "mail")
	public String getMail() {
		return mail;
	}
	@Column(name = "tel1")
	public String getTel1() {
		return tel1;
	}
	@Column(name = "tel2")
	public String getTel2() {
		return tel2;
	}
	@Column(name = "deliveryAdress")
	public String getDeliveryAdress() {
		return deliveryAdress;
	}
	@Column(name = "rating")
	public long getRating() {
		return rating;
	}
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")//, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Action> getActions() {
        return this.actionList;
    }
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")//, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Commission> getOrders() {
        return this.orderList;
    }
	public void setUserId(int userId) {
		this.userId=userId;
	}
	public void setLogin(String login) {
		this.login=login;
	}

	public void setPassword(String password) {
		this.password=password;
	}
	public void setName(String name) {
		this.name=name;
	}

	public void setMail(String mail) {
		this.mail=mail;
	}
	public void setEDRPOU(String EDRPOU) {
		this.EDRPOU=EDRPOU;
	}

	public void setTel1(String tel1) {
		this.tel1=tel1;
	}
	public void setTel2(String tel2) {
		this.tel2=tel2;
	}

	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}


	public void setContactTelDetails(Set<Action> actionList) {
        this.actionList = actionList;
    }
    public void setActions(Set<Action> actionList) {
        this.actionList = actionList;
    }
    public void setOrders(Set<Commission> orderList) {
         this.orderList = orderList;
    }
	@Override
	public String toString() {
		return "User [userId=" + userId + "login=" + login + ", password=" + password + 
						", name=" + name + ", mail=" + 
						mail + ", EDRPOU=" + EDRPOU + 
						", tel1=" + tel1 + ", tel2=" + tel2 + ", deliveryAdress=" + deliveryAdress +", rating=" + rating +"]";
	}
}