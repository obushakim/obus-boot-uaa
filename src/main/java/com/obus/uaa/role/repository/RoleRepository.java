package com.obus.uaa.role.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obus.uaa.role.domain.Role;
import com.obus.uaa.user.domain.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{

}
