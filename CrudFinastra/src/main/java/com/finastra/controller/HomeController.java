package com.finastra.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.finastra.entity.Employee;
import com.finastra.srvice.EmployeeService;

@RestController
@RequestMapping("/employee")
public class HomeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String home() {
		return "Home";
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> setData(@RequestBody Employee employee) {
		 Employee setEmployee = employeeService.setEmployee(employee);
		 Map<String , Object>mp=new HashMap<String, Object>();
		 if(setEmployee!=null) {
		 mp.put("UserId", setEmployee.getUserId());
		 mp.put("Status",HttpStatus.OK );
		 return ResponseEntity.ok(mp);
		 }else {
			 mp.put("Status",HttpStatus.BAD_REQUEST );
			 mp.put("Message", "User not created");
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mp);
		 }
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> getLoginData(@RequestBody Employee employee) {
		System.out.println(employee.getPhone());
		System.out.println(employee.getPassword());
	    Employee login = employeeService.getLogin(employee);
	    Map<String, Object> response = new HashMap<>();
	    if (login != null) {
	        response.put("Status", "OK");
	        response.put("Message", "Login Successful");
	        response.put("Employee", login);
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("Status", "FAIL");
	        response.put("Message", "User not found with userId: " + employee.getUserId());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}

	
	
	@GetMapping("/fetching")
	public List<Employee> allData() {
		return employeeService.all();
	}
	
	@GetMapping("/block")
	public List<Employee> allBlockData() {
		return employeeService.blockEmployee();
	}
	@GetMapping("/active")
	public List<Employee> allActiveData() {
		return employeeService.activeEmployee();
	}
	
	
	
	@GetMapping("/check/{userId}")
	public ResponseEntity<Map<String, Object>> setUpdate(@PathVariable String userId) {
	    Employee employee = employeeService.setMod(userId);
	    Map<String, Object> response = new HashMap<>();

	    if (employee != null) {
	        response.put("Status", "OK");
	        response.put("Message", "User found with userId: " + userId);
	        response.put("employee", employee);  
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("Status", "BAD_REQUEST");
	        response.put("Message", " User not found with userId: " + userId);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> getDelete(@PathVariable String userId) {
		 Boolean setDel = employeeService.setDel(userId);
		 if(setDel) {
			 return ResponseEntity.ok(" Employee deleted successfully");
		 }
		 return ResponseEntity.status(HttpStatus.NOT_FOUND)
                 .body(" user_id doesnot exist");
	}
	
	
	
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> updateEmployee(@RequestBody Employee emp) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        Employee updated = employeeService.updateEmployee(emp);
	        response.put("Status", "OK");
	        response.put("Message", " Employee updated successfully!");
	        response.put("employee", updated);
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        response.put("Status", "BAD_REQUEST");
	        response.put("Message", " Update failed: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	}

	
	
	

}
