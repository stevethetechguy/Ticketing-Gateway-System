package com.synergisticit.service;

import java.util.List;
import com.synergisticit.domain.Employee;

public interface IEmployeeService {
	Employee save(Employee employee);
	List<Employee> findAll();
	Employee findById(Long id);
	Employee update(Long id, Employee employee);
	void delete(Long id);
}
