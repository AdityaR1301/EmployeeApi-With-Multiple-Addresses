package com.boot.restapi.Employeeontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.restapi.EmpService.EmployeeService;
import com.boot.restapi.Entity.Employee;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@GetMapping("/getall")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(empService.getAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
		return ResponseEntity.ok(empService.getEmployee(id));
	}

	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp) {
		return ResponseEntity.ok(empService.saveEmployee(emp));
	}

}
