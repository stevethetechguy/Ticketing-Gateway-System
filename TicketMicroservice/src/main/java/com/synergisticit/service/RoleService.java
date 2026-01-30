package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {

	@Autowired
	RoleRepository roleRepository;
	@Override
	public Role save(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Role with ID " + id + " not found"));
	}

	@Override
	public Role update(Long id, Role role) {
		// TODO Auto-generated method stub
		Role existingRole = roleRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
		
		existingRole.setName(role.getName());
		
		return roleRepository.save(existingRole);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		roleRepository.deleteById(id);
	}

}

