package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_roles")
public class GroupRoles {

	@Id
	@Column
	private Long group_roles_id;

	@Column
	private String role;

	public GroupRoles() {
		super();
	}

	public GroupRoles(Long group_roles_id, String role) {
		super();
		this.group_roles_id = group_roles_id;
		this.role = role;
	}

	public Long getGroup_roles_id() {
		return group_roles_id;
	}

	public void setGroup_roles_id(Long group_roles_id) {
		this.group_roles_id = group_roles_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "GroupRoles [group_roles_id=" + group_roles_id + ", role=" + role + "]";
	}

}