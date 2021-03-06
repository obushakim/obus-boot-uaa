package com.obus.uaa.user.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.obus.uaa.common.audit.Auditable;
import com.obus.uaa.roleuser.domain.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends Auditable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "email")
	private String email;
	
    @OneToMany(mappedBy="user")
    private List<RoleUser> roleUserList;
}
