package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;

@Service
public interface IRoleService {
	Role save(Role role);
	List<Role> findAll();
	Role findById(Long id);
	Role update(Long id, Role role);
	void deleteById(Long id);
}
