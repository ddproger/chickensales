package ua.goryainov.hibernate.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "orderproduct")

public class OrderProduct {	
	@EmbeddedId
    private OrderProductId orderProductId;
	private float price;
	private int count;
	@ManyToOne
	@JoinColumn( name = "orderId")
	private Commission commission;
	private Product product;
	public OrderProduct(){
		orderProductId=new OrderProductId();
	}
	public OrderProduct(Commission order, Product product,float price,int count){
		orderProductId=new OrderProductId();
		this.commission=order;
		this.product=product;
		this.price=price;
		this.count=count;
	}
	@EmbeddedId
	public OrderProductId getOrderProductId(){
		return orderProductId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "orderId",insertable = false, updatable = false)
	@MapsId("orderId")
	public Commission getCommission() {
        return this.commission;
    }
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "productId", insertable = false, updatable = false)
	@MapsId("productId")
	public Product getProduct() {
        return this.product;
    }

    @Column( name = "price")
    public float getPrice() {
        return this.price;
    }
    @Column( name = "count")
    public int getCount() {
        return this.count;
    }
	public void setCommission(Commission order) {
        this.commission=order;
    }
	public void setProduct(Product product){
        this.product = product;
    }
	public void setOrderProductId(OrderProductId orderProductId){
		this.orderProductId = orderProductId;
	}
	public void setPrice(float price) {
		this.price = price;
	}
    public void setCount(int count) {
    	this.count = count;
    }
	@Override
	public String toString() {
		return "OrderProduct [orderProductId=" + orderProductId + ", productName=" + product.getName() + ", productPhoto=" + product.getPhoto()  + ", count=" + count +", price=" + price + "]";
	}
}
