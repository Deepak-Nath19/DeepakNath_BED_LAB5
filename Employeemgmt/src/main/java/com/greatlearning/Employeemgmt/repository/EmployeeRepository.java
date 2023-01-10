package com.greatlearning.Employeemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.Employeemgmt.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
