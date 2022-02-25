package com.obus.uaa.roleuser.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.obus.uaa.common.audit.Auditable;
import com.obus.uaa.role.domain.Role;
import com.obus.uaa.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLE_USER")
public class RoleUser extends Auditable<String> {

	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name="id_user", nullable=false)
	private User user;
	

	@ManyToOne
    @JoinColumn(name="id_role", nullable=false)
	private Role role;
}
