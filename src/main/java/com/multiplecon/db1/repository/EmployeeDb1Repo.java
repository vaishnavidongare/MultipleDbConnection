package com.multiplecon.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multipledbcon.db1.model.Employee;

public interface EmployeeDb1Repo extends JpaRepository<Employee, Integer> {

}
