package ua.goryainov.hibernate.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

@Entity
@Table(name = "action")
public class Action {
	private int actionId;	
	private String name;	
	private String endDate;	
	private Boolean complete;	
	private User user;
	private ActionType actionType;
	public Action(){};
	public Action(String name, User user){
		this.name=name;
		this.user = user;
		this.complete = true;
		this.endDate=new Date().toString();
	}
	public Action(String name, User user,ActionType actionType){
		this.name=name;
		this.user = user;
		this.complete = true;
		this.endDate=new Date().toString();
		this.actionType=actionType;
	}
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "userId", nullable = false)
    public User getUser() {
        return this.user;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "actionTypeId", nullable = false)
    public ActionType getActionType() {
        return this.actionType;
    }
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actionId" , unique = true, nullable = false)
    public int  getActionId(){
    	return this.actionId;
    }
    @Column(name = "name" , length = 50)
    public String  getName(){
    	return this.name;
    }
    @Column(name = "endDate")
    public String  getEndDate(){
    	return this.endDate;
    }
    @Column(name = "complete")
    public Boolean getComplete(){
    	return this.complete;
    } 
    public void setName(String name) {
        this.name = name;
    }
    public void setActionId(int actionId) {
        this.actionId = actionId;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
    @Override
	public String toString() {
		return "Action [name=" + name + ", endDate=" + endDate + ", complete=" + complete + ", user=" + user +  "]";
	}
}
