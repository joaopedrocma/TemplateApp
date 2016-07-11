package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@Column
	private Long user_id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private Long user_role_id;

	@Column
	private Long group_id;

	@Column
	private Boolean enabled;

	public Users() {
		super();
	}

	public Users(Long user_id, String username, String password, Long user_role_id, Long group_id, Boolean enabled) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.user_role_id = user_role_id;
		this.group_id = group_id;
		this.enabled = enabled;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(Long user_role_id) {
		this.user_role_id = user_role_id;
	}

	public Long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", user_role_id="
				+ user_role_id + ", group_id=" + group_id + ", enabled=" + enabled + "]";
	}
}
