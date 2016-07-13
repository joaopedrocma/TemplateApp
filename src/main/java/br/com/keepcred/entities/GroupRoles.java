package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_roles")
public class GroupRoles {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long group_role_id;

	@Column
	private String role;

	public GroupRoles() {
		super();
	}

	public GroupRoles(Long group_role_id, String role) {
		super();
		this.group_role_id = group_role_id;
		this.role = role;
	}

	public Long getGroup_role_id() {
		return group_role_id;
	}

	public void setGroup_role_id(Long group_role_id) {
		this.group_role_id = group_role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "GroupRoles [group_role_id=" + group_role_id + ", role=" + role + "]";
	}

}