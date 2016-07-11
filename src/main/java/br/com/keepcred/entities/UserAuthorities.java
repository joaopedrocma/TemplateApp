package br.com.keepcred.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_authorities")
public class UserAuthorities {

	@Id
	@Column
	private Long user_authority_id;

	@Column
	private Long user_id;

	@Column
	private String authority;

	public UserAuthorities() {
		super();
	}

	public UserAuthorities(Long user_authority_id, Long user_id, String authority) {
		super();
		this.user_authority_id = user_authority_id;
		this.user_id = user_id;
		this.authority = authority;
	}

	public Long getUser_authority_id() {
		return user_authority_id;
	}

	public void setUser_authority_id(Long user_authority_id) {
		this.user_authority_id = user_authority_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User_Authorities [user_authority_id=" + user_authority_id + ", user_id=" + user_id + ", authority="
				+ authority + "]";
	}
}
