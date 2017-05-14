package ua.goryainov.hibernate.model;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {
	
	private int statusId;
	
	private String name;
		
	private Set<Commission> orderList = new HashSet<Commission>(0);
		
	public Status(){
		name="";
	}
	public Status(String name){
		this.name=name;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
	public Set<Commission> getOrderList() {
		return this.orderList;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "statusId")
	public int getStatusId() {
		return this.statusId;
	}
	@Column(name = "name")
	public String getName() {
		return this.name;
	}
    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn( name = "product")

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOrderList(Set<Commission> orderList) {
		this.orderList = orderList;
	}
	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", orderList=" + orderList + ", name=" + name + "]";
	}	

}
