package br.biblioteca.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Role {
	
	@Id
	@GeneratedValue
	private Long id;

	String role;
	
	public Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + "]";
	}
}
