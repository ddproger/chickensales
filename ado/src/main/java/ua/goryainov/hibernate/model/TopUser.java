package ua.goryainov.hibernate.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class TopUser {

	private String name;
	private String EDRPOU;
	private String mail;
	private String tel1;
	private String tel2;
	private String deliveryAdress;
	private long purchase;

	public TopUser() {
		this.name = "";
		this.EDRPOU = "";
		this.mail = "";
		this.tel1 = "";
		this.tel2 = "";
		this.deliveryAdress = "";
		purchase = 0;
	}

	public TopUser(String name,
                   String EDRPOU, String mail, String tel1,
                   String tel2, String deliveryAdress, long purchase) {
		this.name = name;
		this.EDRPOU = EDRPOU;
		this.mail = mail;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.deliveryAdress = deliveryAdress;
		this.purchase = purchase;
	}

	public String getName() {
		return name;
	}
	public String getEDRPOU() {
		return EDRPOU;
	}
	public String getMail() {
		return mail;
	}
	public String getTel1() {
		return tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public String getDeliveryAdress() {
		return deliveryAdress;
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
	@Override
	public String toString() {
		return "User [name=" + name + ", mail=" +
						mail + ", EDRPOU=" + EDRPOU + 
						", tel1=" + tel1 + ", tel2=" + tel2 + ", deliveryAdress=" + deliveryAdress +",purchase="+ this.purchase+" ]";
	}
}