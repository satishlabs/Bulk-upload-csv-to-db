package com.estuate.csv.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.estuate.csv.model.Employee;

public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = {"EmpId", "Firstname", "Lastname", "Contact", "Email"};
	
	//check if a file has CSV format or not
	public static boolean hasCSVFormat(MultipartFile file) {
		if(!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}
	
	//read InputStream of a file, return a list of Employees
	public static List<Employee> csvToEmployee(InputStream is){
		  try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			        CSVParser csvParser = new CSVParser(fileReader,
			            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){
			 List<Employee> employees = new ArrayList<Employee>();
			 
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			for(CSVRecord csvRecord : csvRecords) {
				Employee employee = new Employee(
						Long.parseLong(csvRecord.get("EmpId")), 
						csvRecord.get("Firstname"), 
						csvRecord.get("Lastname"), 
						Long.parseLong(csvRecord.get("Contact")), 
						csvRecord.get("Email")
						);
				employees.add(employee);
			}
			System.out.println("Employees List: "+employees);
			return employees;
			
		 }catch (Exception e) {
			 throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
		
		
	}

	public static ByteArrayInputStream employeeToCSV(List<Employee> employees) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		try(
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)
				){
			for(Employee employee : employees) {
				List<String> empData = Arrays.asList(
						String.valueOf(employee.getId()),
						String.valueOf(employee.getEmpId()),
						employee.getFirstname(),
						employee.getLastname(),
						String.valueOf(employee.getContact()),
						employee.getEmail()
						);
				
				csvPrinter.printRecord(empData);
			}
			
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
			
		}catch (IOException e) {
		      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	}
}
