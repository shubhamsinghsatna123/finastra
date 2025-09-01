package com.finastra.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finastra.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {

	Optional<Employee> findByPhone(Long phone);

	Employee findByEmployeeId(String employeeId);

	Employee findByEmailIdIgnoreCase(String emailId);

	
	@Query(value = "SELECT * FROM shubham_emp WHERE del_flg=true", nativeQuery = true)
	List<Employee> findDeletedEmployees();
	
	@Query(value = "SELECT * FROM shubham_emp WHERE del_flg=false", nativeQuery = true)
	List<Employee> findActiveEmployees();

	Optional<Employee> findByUserIdIgnoreCase(String userId);






}
