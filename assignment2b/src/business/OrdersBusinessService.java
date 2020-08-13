package business;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;


import beans.Order;

/**
 * Session Bean implementation class OrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterface.class)
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterface {

	List<Order> orders = new ArrayList<Order>();
	Order order = new Order();
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;

	
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
    	orders.add(new Order("1234", "This is Product1", (float)10.00, 1));
		orders.add(new Order("1235", "This is Product2", (float)10.00, 1));
		orders.add(new Order("1236", "This is Product3", (float)10.00, 1));
		orders.add(new Order("1237", "This is Product4", (float)10.00, 1));
		orders.add(new Order("1238", "This is Product5", (float)10.00, 1));
		orders.add(new Order("1239", "This is Product6", (float)10.00, 1));
		orders.add(new Order("1240", "This is Product7", (float)10.00, 1));
    }

	/**
     * @see OrdersBusinessInterface#test()
     */
    @Override
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
		
	}
	
	@Override
	public void sendOrder(Order order) {
		// Send a Message for an Order
		try 
		{
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage message1 = session.createTextMessage();
			message1.setText("This is test message");
			messageProducer.send(message1);
			connection.close();
			System.out.println("Successful message connection");
			System.out.println(order.toString());
			
			ObjectMessage message2 = session.createObjectMessage();
			message2.setObject((Serializable) order);
			messageProducer.send(message2);
		} 
		catch (JMSException e) 
		{
			System.out.println("fail");
			e.printStackTrace();
		}
	}
}
