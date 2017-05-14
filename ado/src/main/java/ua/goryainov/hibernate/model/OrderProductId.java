package ua.goryainov.hibernate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class OrderProductId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderId;
	private int productId;
	public OrderProductId(){
		
	}
	public OrderProductId(int orderId,int productId){
		this.orderId = orderId;
		this.productId = productId;
	}
	public int getOrderId(){
		return orderId;
	}
	public int getProductId(){
		return productId;
	}
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	public void setProductId(int productId){
		this.productId=productId;
	}
	
}
