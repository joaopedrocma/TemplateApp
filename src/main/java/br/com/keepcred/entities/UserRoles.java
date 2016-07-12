package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRoles {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_roles_id;

	@Column
	private String role;

	public UserRoles() {
		super();
	}

	public UserRoles(Long user_roles_id, String role) {
		super();
		this.user_roles_id = user_roles_id;
		this.role = role;
	}

	public Long getUser_roles_id() {
		return user_roles_id;
	}

	public void setUser_roles_id(Long user_roles_id) {
		this.user_roles_id = user_roles_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRoles [user_roles_id=" + user_roles_id + ", role=" + role + "]";
	}

	
}