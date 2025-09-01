package com.finastra.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.finastra.entity.Employee;

public class SecurityUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	Employee employee;

	public SecurityUserDetails(Employee employee) {
		this.employee=employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getUserId();
	}

}
