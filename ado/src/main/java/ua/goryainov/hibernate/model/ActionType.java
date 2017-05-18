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
@Table(name = "actionType")
public class ActionType {
	private int actionTypeId;	
	private String name;
	private Set<Action> actions = new HashSet<Action>(0);
		
	public ActionType(){};
	public ActionType(String name){
		this.name=name;
	}   
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actionTypeId" , unique = true, nullable = false)
    public int  getActionTypeId(){
    	return this.actionTypeId;
    }
    @Column(name = "name" , length = 50)
    public String  getName(){
    	return this.name;
    }
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "actionType")
    public Set<Action> getActions(){
		return actions;
	}	
	public void setActionTypeId(int actionTypeId) {
        this.actionTypeId = actionTypeId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }    
    @Override
	public String toString() {
		return "actionType [name=" + name + "]";
	}
}
