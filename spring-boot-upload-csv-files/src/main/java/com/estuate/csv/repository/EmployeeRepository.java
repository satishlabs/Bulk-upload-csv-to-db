package com.estuate.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estuate.csv.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
