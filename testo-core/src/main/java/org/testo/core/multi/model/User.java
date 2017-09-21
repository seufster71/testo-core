package org.testo.core.multi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String Firstname;
	private String Lastname;
	
	public User() {}
	
	public User(String firstname, String lastname) {
		this.setFirstname(firstname);
		this.setLastname(lastname);
	}
	
	@Column(name = "firstname")
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	
	@Column(name = "lastname")
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	
	
}
