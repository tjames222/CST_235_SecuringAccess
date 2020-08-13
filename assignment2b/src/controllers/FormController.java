package controllers;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Order;
import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;

@ManagedBean
@ViewScoped
public class FormController {
	
	@Inject
	OrdersBusinessInterface services;
	
	@EJB
	MyTimerService timer;
	
	////////// Method for returning string of Test Response view
	public String onSubmit() throws SQLException {
		
		// Using FacesContext object to get data from form
		FacesContext context = FacesContext.getCurrentInstance();
		
		// Creating a User object and injecting data from form
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Test printing to console to verify data passed correctly
		System.out.println("First Name: " + user.getfirstName());
		
		// Prints message to show which business service is being used in the beans .xml
		services.test();
		
		// Start a timer when the login is clicked
		timer.setTimer(10000);
		
		// Puts the user object into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		// Gets data from connected database
		getAllOrders();
		
		// Inserts orders
		insertOrder();
		getAllOrders();
		
		// Activates response page in browser
		return "TestResponse.xhtml";
	}
	
	//////////Method for returning string of Test Response view
	public String onFlash() {
		
		// Using FacesContext object to get data from form
		FacesContext context = FacesContext.getCurrentInstance();
		
		// Creating a User object and injecting data from form
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Test printing to console to verify data passed correctly
		System.out.println("First Name: " + user.getfirstName());
		
		// Puts the user object into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		
		// Activates response page in browser		
		return "TestResponse2.xhtml";
	}
	
	//////////Method for returning services
	public OrdersBusinessInterface getService() {
		
		return services;
	}
	//////////Method for communicating with the database
	private void getAllOrders() {
		
		// Variables to hold connection data
		String URL = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String pass = "root";
		
		// SQL Variables for connection
		Connection conn = null;
		Statement stmt = null ;
		ResultSet rs = null;
		
		// Connect to database catch if failure
		try {
			conn = DriverManager.getConnection(URL, username, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM testapp.orders");
			
			// Process the rows for the table
			while(rs.next()) {
				System.out.println("id = " + rs.getInt("id") + "order_no = " + rs.getString("order_no") +
						"product_name = " + rs.getString("product_name") + "price = " + rs.getFloat("price") +
						"quantity = " + rs.getInt("quantity"));
			}
			
			// Print if successful connection
			System.out.println("Success!!");
		} catch (SQLException e) {
			System.out.println("Failure!!"); // if connection fails
			e.printStackTrace();
		} finally {
			// Close the db connection
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//////////Method for inserting order
	private void insertOrder() {
		
		// Variables to hold connection data
		String URL = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String pass = "root";
		
		String SQLInsert = "INSERT INTO testapp.orders(id, order_no, product_name, price, quantity) VALUES(4, '001122334455', 'This was inserted new', 25.00, 100)";
		
		// SQL Variables for connection
		Connection conn = null;
		Statement stmt = null ;
		
		// Connect to database catch if failure
		try {
			conn = DriverManager.getConnection(URL, username, pass);
			stmt = conn.createStatement();
			stmt.executeUpdate(SQLInsert);
			
			// Print if successful connection
			System.out.println("Success!!");
		} catch (SQLException e) {
			System.out.println("Failure!!"); // if connection fails
			e.printStackTrace();
		} finally {
			// Close the db connection
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	////////// Method for sending an order
	public String onSendOrder() {
		
		// Test order data
		Order test = new Order("1", "test", (float) 2.00, 10);
		
		// Calls the sendOrder method
		services.sendOrder(test);
		
		return "OrderResponse.xhtml";
	}
	
	////////// Method for logging off
	public String onLogoff() {
		
		// Invalidate the Session to clear the security token
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			
		// Redirect to a protected page (so we get a full HTTP Request) to get Login Page
		return "TestResponse.xhtml?faces-redirect=true";

	}
}
