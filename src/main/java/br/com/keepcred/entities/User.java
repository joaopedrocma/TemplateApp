package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column
	private Long userid;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private Boolean enabled;

	public User() {
		super();
	}

	public User(Long userid, String username, String password, Boolean enabled) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ "]";
	}
}
