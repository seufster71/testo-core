package org.testo.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "userb")
public class Userb extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String Firstname;
	private String Lastname;
	
	public Userb() {}
	
	public Userb(String firstname, String lastname) {
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
