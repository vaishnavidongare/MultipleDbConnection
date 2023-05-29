package com.multipledbcon.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multipledbcon.db1.model.Employee;
import com.multipledbcon.db2.model.EmployeeDb2;
import com.multipledbcon.service.EmployeeService;



@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	public EmployeeService es;
	
	@PostMapping
	public Employee addEmployee(@RequestBody Employee e) {
		 Employee emp1 = es.addEmployee(e);
		 //es.updateTable2(emp1);
			return emp1;	
	}
	
	@PutMapping("/{id}")
	public void updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		es.updateEmployee(id, employee);
	}
	
	@GetMapping("/map/data")
	public ResponseEntity<Map<Integer,String>> getDataIntoMap(){
		Map<Integer,String> map = new EmployeeService().getDataIntoMap();
				//es.getDataIntoMap();
		return ResponseEntity.ok(map);
	}
	
	
	@GetMapping("/data/fromcache")
	public ResponseEntity<List<EmployeeDb2>> getEmployee(){
		List<EmployeeDb2> empdb2 = es.getEmployee();
		return ResponseEntity.ok(empdb2);
	}
	
}
