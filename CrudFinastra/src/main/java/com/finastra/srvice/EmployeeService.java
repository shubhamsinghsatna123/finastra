package com.finastra.srvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finastra.entity.Employee;
import com.finastra.repo.EmployeeRepo;

@Service
public class EmployeeService {

    private final AuthenticationManager authenticationManager;

	@Autowired
	private EmployeeRepo employeeRepo;

    EmployeeService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
	public Employee setEmployee(Employee employee) {
		Employee e2 = employeeRepo.findByEmployeeId(employee.getEmployeeId());
		Employee e1 = employeeRepo.findByEmailIdIgnoreCase(employee.getEmailId());
		if ( e1 != null || e2 != null) {
			throw new IllegalArgumentException("Employee Already Registered");
		}
		LocalDateTime now = LocalDateTime.now();
		String employee_ID = employee.getEmployeeId();
		String name = employee.getName();
		if (employee_ID == null || employee_ID.isEmpty()) {
			throw new IllegalArgumentException("Employee_ID cannot be null or empty");
		}
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty");
		}
		String empIdPart = employee_ID.substring(0, Math.min(4, employee_ID.length()));
		String namePart = name.substring(0, Math.min(4, name.length())).toLowerCase();
		String userId = empIdPart + namePart;
		employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
		employee.setUserId(userId.toLowerCase());
		employee.setCreatedAt(now);
		employee.setModifiedAt(now);
		employee.setDelFlg(false);
		return employeeRepo.save(employee);
	}

	// Login
	 public Employee getLogin(Employee employee) throws AuthenticationException {
	        String identifier = employee.getUserId();
	        if ((identifier == null || identifier.trim().isEmpty()) && employee.getPhone() == null) {
	            throw new BadCredentialsException("UserId or Phone must be provided");
	        }
	        String username = (identifier != null) ? identifier : String.valueOf(employee.getPhone());
	        String password = employee.getPassword();
	        Authentication authenticate = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(username, password)
	        );
	        if (authenticate.isAuthenticated()) {
	            Optional<Employee> customer;
	            if (identifier != null) {
	                customer = employeeRepo.findByUserIdIgnoreCase(identifier);
	            } else {
	                customer = employeeRepo.findByPhone(employee.getPhone());
	            }
	            return customer.orElseThrow(() -> new BadCredentialsException("User not found"));
	        }
	        return null;
	    }


	
	
	// Checking present or not
	public Employee setMod(String userId) {
		return employeeRepo.findById(userId).filter(emp -> Boolean.FALSE.equals(emp.getDelFlg())) 
				.orElse(null);
	}

	// Update data
	public Employee updateEmployee(Employee updatedDetails) {
		updatedDetails.setModifiedAt(LocalDateTime.now());
		return employeeRepo.save(updatedDetails);
	}
	
	
	

	// Deleting
	public boolean setDel(String userId) {
		Employee orElse = employeeRepo.findById(userId).filter(emp -> Boolean.FALSE.equals(emp.getDelFlg())) 
		.orElse(null);
		if (orElse==null) {
			return false;
		}
		Employee emp = employeeRepo.findById(userId).get();
		emp.setModifiedAt(LocalDateTime.now());
		emp.setDelFlg(true);
		employeeRepo.save(emp);
		return true;
	}

	
	// All Data
	public List<Employee> all() {
		return employeeRepo.findAll();
	}


	// Delete Employee
	public List<Employee> blockEmployee() {
		return employeeRepo.findDeletedEmployees();
	}
	
	// Active Employee
	public List<Employee> activeEmployee() {
		return employeeRepo.findActiveEmployees();
	}

}
