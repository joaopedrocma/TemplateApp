package br.com.keepcred.entities;

import java.util.Arrays;

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
	private char[] password;

	@Column
	private Boolean enabled;

	public User() {
		super();
	}

	public User(Long userid, String username, char[] password, Boolean enabled) {
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

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
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
		return "User [userid=" + userid + ", username=" + username + ", password=" + Arrays.toString(password)
				+ ", enabled=" + enabled + "]";
	}

}
