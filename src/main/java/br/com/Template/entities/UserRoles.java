package br.com.Template.entities;

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
	private Long user_role_id;

	@Column
	private String role;

	public UserRoles() {
		super();
	}

	public UserRoles(Long user_role_id, String role) {
		super();
		this.user_role_id = user_role_id;
		this.role = role;
	}

	public Long getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(Long user_role_id) {
		this.user_role_id = user_role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRoles [user_role_id=" + user_role_id + ", role=" + role + "]";
	}

}