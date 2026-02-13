package com.synergisticit.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.synergisticit.model.Employee;
import com.synergisticit.model.Role;
import com.synergisticit.model.RoleName;
import com.synergisticit.model.Status;
import com.synergisticit.model.Ticket;
import com.synergisticit.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
    private RestTemplate restTemplate;

    @Value("${ticket.service.url}") 
    private String userServiceUrl;

    public Employee findByEmail(String email) {
    	String url = userServiceUrl + "/api/employees?email={email}";

    	return restTemplate.getForObject(url, Employee.class, email);
    }
    public Employee findByName(String name) {
    	String url = userServiceUrl + "/api/employees?name={name}";

    	return restTemplate.getForObject(url, Employee.class, name);
    }
    
    public Employee findById(Long id) {
    	String url = userServiceUrl + "/api/employees/" + id;

    	return restTemplate.getForObject(url, Employee.class);
    }
    
    public List<Employee> getEmployeeByRole(RoleName roleName) {
    	
    	String url = userServiceUrl + "/api/employees?roleName=" + roleName;
    	
    	Employee[] employee = restTemplate.getForObject(url, Employee[].class);
    	
        return Arrays.asList(employee);
    }
    
}
