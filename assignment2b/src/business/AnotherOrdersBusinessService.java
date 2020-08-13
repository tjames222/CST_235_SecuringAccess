package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

/**
 * Session Bean implementation class AnotherOrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterface.class)
@Alternative
public class AnotherOrdersBusinessService implements OrdersBusinessInterface {

	List<Order> orders = new ArrayList<Order>();
    /**
     * Default constructor. 
     */
    public AnotherOrdersBusinessService() {
    	orders.add(new Order("1234", "Another Product1", (float)10.00, 1));
		orders.add(new Order("1235", "Another Product2", (float)10.00, 1));
		orders.add(new Order("1236", "Another Product3", (float)10.00, 1));
		orders.add(new Order("1237", "Another Product4", (float)10.00, 1));
		orders.add(new Order("1238", "Another Product5", (float)10.00, 1));
		orders.add(new Order("1239", "Another Product6", (float)10.00, 1));
		orders.add(new Order("1240", "Another Product7", (float)10.00, 1));
    }

	/**
     * @see OrdersBusinessInterface#test()
     */
    @Override
    public void test() {
        System.out.println("Hello from AnotherOrdersBusinessService");
    }

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
		
	}

	@Override
	public void sendOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

}
