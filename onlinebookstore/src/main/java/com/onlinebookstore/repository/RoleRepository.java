package com.onlinebookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.onlinebookstore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(String name);
}
