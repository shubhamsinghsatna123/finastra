package com.finastra.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finastra.entity.Employee;
import com.finastra.repo.EmployeeRepo;

@Service
public class securityUserDetailsService  implements UserDetailsService {
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> customer = employeeRepo.findByUserIdIgnoreCase(username);

		    if (customer.isEmpty()) {
		        try {
		            long mobile = Long.parseLong(username);
		            customer = employeeRepo.findByPhone(mobile);
		        } catch (NumberFormatException e) {
		            throw new UsernameNotFoundException("Invalid email or mobile: " + username);
		        }
		    }
		    if (customer.isEmpty()) {
		        throw new UsernameNotFoundException("Customer not found with email or mobile: " + username);
		    }
		return new SecurityUserDetails(customer.get());
	}

}
