package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    @Autowired
	private EmployeeService employeeService;

	
	//build create employee Rest API
    
	@PostMapping("")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
		
	}
    
    //build  get all employee Rest API
    
   @GetMapping("")
   public List<Employee> getAllEmployees(){
   	return employeeService.getAllEmployees();
   }
    
    
    //build get employee by id Rest API
    //http://localhost:portnumber/api/employees/1

   @GetMapping("{id}")
   //GetMapping id and PathVariable id must be same
   public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
   	return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
   }
    
    //build update employee Rest API
    //http://localhost:portnumber/api/employees/1
    
   @PutMapping("{id}")
   public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,@RequestBody Employee employee){
   	return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
   }
    
    //build delete employee Rest API
    //http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
    	//delete employee from database
    	return new ResponseEntity<String>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
    
	
}
