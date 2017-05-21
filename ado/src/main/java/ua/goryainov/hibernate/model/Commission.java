package ua.goryainov.hibernate.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "commission")
public class Commission {
	
	private int orderId;
    @ManyToOne
    @JoinColumn( name = "userId")
	private User user;
    @Temporal(TemporalType.DATE)
    private Date date;
	private String deliveryAdress;
	private Status status;
	private Set<OrderProduct> orderProductList = new HashSet<OrderProduct>(0);
	//@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)	
	//private Set<OrderProduct> orderProductList = new HashSet<OrderProduct>();
		
	public Commission()
	{
		date=new Date();
		deliveryAdress="";				
	}
	public Commission(User user, Date date, String deliveryAdress, Status status)
	{
		this.user=user;	
		this.date=date;
		this.deliveryAdress=deliveryAdress;
		this.status=status;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderId", unique = true, nullable = false)
    public int getOrderId() {
        return this.orderId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "userId")
    public User getUser() {
        return this.user;
    }
    @Column(name = "date")
	@Type(type="date")
    public Date getDate() {
        return this.date;
    }
    @Column(name = "deliveryAdress")
    public String getDeliveryAdress() {
        return this.deliveryAdress;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "statusId")
    public Status getStatus() {
        return this.status;
    }
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderProduct", cascade = CascadeType.ALL, orphanRemoval = true)
	
    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "products", joinColumns = { 
			@JoinColumn(name = "orderId", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "productId", 
					nullable = false, updatable = false) })*/
    //@ManyToMany(fetch = FetchType.EAGER, mappedBy = "orders")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "commission")
    public Set<OrderProduct> getOrderProduct(){
		return orderProductList;
	}
	public void setProructList(Set<OrderProduct> orderProducts) {
		this.orderProductList = orderProducts;
	}
    public void setUser(User user) {
        this.user = user;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setOrderProduct(Set<OrderProduct> orderProductList){
		this.orderProductList=orderProductList;
	}
    @Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userName=" + user.getName() + ", date=" + date + ", deliveryAdress=" + deliveryAdress + ", status=" + status.getStatusId() + "orderProductList=" + orderProductList + "]";
	}
}
