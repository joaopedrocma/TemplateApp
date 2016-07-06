package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class User_Roles {

	@Id
	@Column
	private Long user_role_id;

	@Column
	private Long userid;

	@Column
	private String role;

	public User_Roles() {
		super();
	}

	public User_Roles(Long user_role_id, Long userid, String role) {
		super();
		this.user_role_id = user_role_id;
		this.userid = userid;
		this.role = role;
	}

	public Long getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(Long user_role_id) {
		this.user_role_id = user_role_id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User_Roles [user_role_id=" + user_role_id + ", userid=" + userid + ", role=" + role + "]";
	}

}