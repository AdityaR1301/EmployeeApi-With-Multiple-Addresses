package com.boot.restapi.EmpService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.restapi.Entity.Address;
import com.boot.restapi.Entity.Employee;
import com.boot.restapi.Repository.EmployeeRepository;
import com.boot.restapi.exception.ResponseNotFound;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	public List<Employee> getAll() {
		return empRepo.findAll();
	}

	public Employee getEmployee(int id) {

		return empRepo.findById(id).orElseThrow(() -> new ResponseNotFound("id not found"));
	}

	public Employee saveEmployee(Employee emp) {

		Optional<Employee> savedEmp = empRepo.findByEmail(emp.getEmail());

		if (savedEmp.isPresent()) {
			for (Address address : emp.getEaddr()) {
				address.setEmployee(savedEmp.get());
				savedEmp.get().getEaddr().add(address);
			}

		} else {
			if (emp.getEaddr() != null) {
				for (Address address : emp.getEaddr()) {
					address.setEmployee(emp);
				}
			}
			savedEmp = Optional.of(emp);
		}
		return empRepo.save(savedEmp.get());
	}

}
