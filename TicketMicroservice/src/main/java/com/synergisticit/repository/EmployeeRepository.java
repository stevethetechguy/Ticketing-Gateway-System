package com.synergisticit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.RoleName;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);
	
	Optional<Employee> findByName(String name);

	
	@Query("SELECT e FROM Employee e JOIN e.roles r WHERE r.name = :roleName")
    List<Employee> findEmployeesByRoleName(@Param("roleName") RoleName roleName);
}
