package beans;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@SessionScoped
public class User {
	
	// Declaring Properties
	@NotNull()
	@Size(min=5, max=15)
	String firstName;
	
	@NotNull()
	@Size(min=5, max=15)
	String lastName;
	
	////////// Mehtod for getting users
	@PostConstruct
	public void init() {
	// Get the logged in Principle
	Principal principle= FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		if(principle == null)
		{
			setFirstName("Unknown");
			setlastName("");
		}
		else
		{
			setFirstName(principle.getName());
			setlastName("");
		}
	}
	
	////////// Getter and setter for firstName
	public String getfirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstNameIn) {
		this.firstName = firstNameIn;
	}
	
	////////// Getter and setter for lastName
	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastNameIn) {
		this.lastName = lastNameIn;
	}
	
	////////// Default constructor
	public User() {
		firstName = "Tim";
		lastName = "James";
	}

}
