package br.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Role {
	
	@Id
	@GeneratedValue
	private Long id;

	private String role;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Role(String role, User usuario) {
		this.role = role;
		this.user = usuario;
	}

	public Role() {}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUsuario(){
		return user;
	}

	public Long getId(){
		return id;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + "]";
	}
}
