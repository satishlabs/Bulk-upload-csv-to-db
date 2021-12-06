package com.estuate.csv.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.estuate.csv.helper.CSVHelper;
import com.estuate.csv.model.Employee;
import com.estuate.csv.repository.EmployeeRepository;

@Service
public class CSVService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void save(MultipartFile file) {
		try {
			List<Employee> employees = CSVHelper.csvToEmployee(file.getInputStream());
			employeeRepository.saveAll(employees);
		}catch (Exception e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public ByteArrayInputStream downloadEmployeesCSV() {
		List<Employee> employees = employeeRepository.findAll();
		
		ByteArrayInputStream in = CSVHelper.employeeToCSV(employees);
		return in;
	}
}
