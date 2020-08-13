package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class Orders {
	
	////////// List to hold order objects
	List<Order> orders = new ArrayList<Order>();
	
	////////// Constructor
	public Orders() {
		orders.add(new Order("1234", "Product1", (float)10.00, 1));
		orders.add(new Order("1235", "Product2", (float)10.00, 1));
		orders.add(new Order("1236", "Product3", (float)10.00, 1));
		orders.add(new Order("1237", "Product4", (float)10.00, 1));
		orders.add(new Order("1238", "Product5", (float)10.00, 1));
		orders.add(new Order("1239", "Product6", (float)10.00, 1));
		orders.add(new Order("1240", "Product7", (float)10.00, 1));
	}

	////////// Getters and setters
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
