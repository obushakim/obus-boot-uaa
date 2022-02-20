package com.obus.uaa.common.audit;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;


/**
 * Parent class buat domain yg perlu auditing atau logging di data nya
 * 
 * @author Obus
 *
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -9037606160613198635L;
	
	@Id
	@GeneratedValue
	@Type(type = "uuid-char")
	@Column(length = 36)
	private UUID id;
	
	@CreatedBy
	@Column(name = "created_by")
	protected T createdBy;
		
	@CreatedDate
	@Column(name = "created_date")
	protected LocalDateTime createdDate;
	
	@LastModifiedBy
	@Column(name = "last_modified_by")
	protected T lastModifiedBy;
		
	@LastModifiedDate
	@Column(name = "last_modified_date")
	protected LocalDateTime lastModifiedDate;
	
	@Version
	@Column(name = "version")
	protected Long version;
	
	@Column(name = "deleted")
	protected Boolean deleted;
}
