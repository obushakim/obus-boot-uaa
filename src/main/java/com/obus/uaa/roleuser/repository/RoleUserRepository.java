package com.obus.uaa.roleuser.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obus.uaa.roleuser.domain.RoleUser;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, UUID>{

}
