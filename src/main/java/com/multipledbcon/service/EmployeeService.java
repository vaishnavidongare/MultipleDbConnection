package com.multipledbcon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.multiplecon.db1.repository.EmployeeDb1Repo;
import com.multipledbcon.db1.model.Employee;
import com.multipledbcon.db2.model.EmployeeDb2;
import com.multipledbcon.db2.repository.EmployeeDb2Repo;



@Service
public class EmployeeService {
	@Autowired
	public EmployeeDb1Repo erepo1;
	
	@Autowired
	private EmployeeDb2Repo erepo2;
	
	@Autowired
	private CacheManager cacheManager;
	
	private static Map<Integer,String> dmap = new HashMap<>();
	//To add employee data in table1
	public Employee addEmployee(Employee emp) {
		return erepo1.save(emp);

	}
	
	//To update table1 of Db1 to the table2 of db2 by every 1 min
	@Scheduled(fixedRate = 60000)
	public void updateTable2() {
		List<Employee> emp = erepo1.findAll();
		for(Employee e:emp) {
		 EmployeeDb2 e2 = new EmployeeDb2();
			e2.setId(e.getId());
			e2.setAadhar_no(e.getAadhar_no());
			erepo2.save(e2);
		}
	}
	
	
	//To update data in table1
	public void updateEmployee(Integer id, Employee employee) {
		erepo1.save(employee);

	}
	
	
	//To update data from table2 to static map
	@Scheduled(fixedRate = 300000)
	public void updateData() {
		
		Iterable<EmployeeDb2> emp=erepo2.findAll();
		Map<Integer,String> map = new HashMap<>();
		for(EmployeeDb2 e:emp) {
			map.put(e.getId(),e.getAadhar_no());
		}
		dmap=map;
	}
	
	//Update data from table 2 to cache
	@Scheduled(fixedRate = 300000)
	public void updateDataToCache() {
		Iterable<EmployeeDb2> emp = erepo2.findAll();
		Cache<String, Object> cache = (Cache<String, Object>) cacheManager.getCache("employees").getNativeCache();
		for (EmployeeDb2 employeeDb2 : emp) {
			cache.put(employeeDb2.getId().toString(),employeeDb2.getAadhar_no());
		}
	}
		
	
	
	//To get data from table2 in key-value format
	public Map<Integer,String> getDataIntoMap(){
		return dmap;
	}
	@Cacheable(value = "employees")
	public List<EmployeeDb2> getEmployee(){
		return erepo2.findAll();
	}
	
	

	
}
