package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class Groups {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long group_id;

	@Column
	private Long group_roles_id;

	@Column
	private String groupname;

	public Groups() {
		super();
	}

	public Groups(Long group_id, Long group_roles_id, String groupname) {
		super();
		this.group_id = group_id;
		this.group_roles_id = group_roles_id;
		this.groupname = groupname;
	}

	public Long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	public Long getGroup_roles_id() {
		return group_roles_id;
	}

	public void setGroup_roles_id(Long group_roles_id) {
		this.group_roles_id = group_roles_id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Override
	public String toString() {
		return "Groups [group_id=" + group_id + ", group_roles_id=" + group_roles_id + ", groupname=" + groupname + "]";
	}

}
