package beans;

import javax.faces.bean.ManagedBean;
import javax.xml.bind.annotation.XmlRootElement;


@ManagedBean
@XmlRootElement(name="Order")
public class Order {
	
	////////// Properties
	String orderNum = "";
	String productName = "";
	float price = 0;
	int quantity = 0;
	
	////////// Default constructor
	public Order() {
		orderNum = "";
		productName = "";
		price = 0;
		quantity = 0;
	}
	
	//////////Constructor with initializers
	public Order(String orderNum, String productName, float price, int quantity) {
		super();
		this.orderNum = orderNum;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	
	////////// Getters and Setters
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
