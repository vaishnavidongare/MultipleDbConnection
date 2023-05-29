package com.multipledbcon.db2.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multipledbcon.db2.model.EmployeeDb2;

public interface EmployeeDb2Repo extends JpaRepository<EmployeeDb2, Integer> {
//	@Cacheable(value = "employees",key = "#root.methodName")
//	List<EmployeeDb2> findAll();
}
