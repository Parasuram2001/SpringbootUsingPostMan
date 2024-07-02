package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepo.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}
//		else {
//			throw new ResourceNotFoundException("Employee","Id",id);
//		}
		
		//instead of writing above code we can simplify the code using lamda expression
		return employeeRepo.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee","Id",id));
	}
	
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//we need to check whether the employee with given id is exist in database or not;
		Employee existingEmployee = employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//save existing employee to database
		employeeRepo.save(existingEmployee);
		return existingEmployee;
	}
	
	@Override
	public String deleteEmployee(long id) {
		
		//check whether a employee exist in database or not
		if (employeeRepo.existsById(id)){
			employeeRepo.deleteById(id);
			return "Deleted!";
		}
		else{
			return "Data not found";
		}
		
		
		
	}
}
