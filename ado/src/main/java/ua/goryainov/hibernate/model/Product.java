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
@Table(name = "product")
public class Product {	
	private int productId;	
	private String name;	
	private float price;	
	private String description;	
	private String photo;	
	
	private Set<OrderProduct> orderProductList = new HashSet<OrderProduct>(0);
	//private Set<Order> orderList = new HashSet<Order>(0);
	public Product(){
		this.name = "";
		this.price = 0;
		this.name = "";
		this.description = "";
		this.photo = "";
	}
	public Product(String name, float price, String description, String photo) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.photo = photo;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productId", unique = true, nullable = false)
	public int getProductId() {
		return this.productId;
	}
	@Column(name = "name")
	public String getName() {
		return this.name;
	}
	@Column(name = "price")
	public float getPrice() {
		return this.price;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "orderProducts", cascade = CascadeType.ALL, orphanRemoval = true)
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "orderproduct", joinColumns = { 
			@JoinColumn(name = "productId", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "orderId", 
					nullable = false, updatable = false) })
	*/
	//@ManyToMany(fetch = FetchType.EAGER, mappedBy = "products")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    public Set<OrderProduct> getOrderProduct(){
		return orderProductList;
	}
	public void setProructList(Set<OrderProduct> orderProducts) {
		this.orderProductList = orderProducts;
	}
	//public Set<Order> getOrders() {
	//	return this.orderList;
	//}
	@Column(name = "photo")
	public String getPhoto(){
		return this.photo;
	}
	//public void setOrders(Set<Order> orderList) {
	//	this.orderList = orderList;
	//}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setOrderProduct(Set<OrderProduct> orderProductList){
		this.orderProductList=orderProductList;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", photo=" + photo + ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}
	
}
