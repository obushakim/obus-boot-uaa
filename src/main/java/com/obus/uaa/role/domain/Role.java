package com.obus.uaa.role.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
@Table(name = "ROLE")
public class Role extends Auditable<String> {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	@Size(max=255)
	private String code;
	
	@Column(name = "name")
	@Size(max=255)
	private String name;
	
    @OneToMany(mappedBy="role")
    private List<RoleUser> roleUser;
}
