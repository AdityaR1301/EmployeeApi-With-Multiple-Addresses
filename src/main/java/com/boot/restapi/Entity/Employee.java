package com.boot.restapi.Entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eid;
	private String ename;
	private String email;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	List<Address> eaddr;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public List<Address> getEaddr() {
		return eaddr;
	}

	public void setEaddr(List<Address> eaddr) {
		this.eaddr = eaddr;
	}

	public Employee() {
		super();
	}

	public Employee(int eid, String ename, String email, List<Address> eaddr) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.email = email;
		this.eaddr = eaddr;

		if (eaddr != null) {
			eaddr.forEach(address -> address.setEmployee(this));
		}
	}
}
