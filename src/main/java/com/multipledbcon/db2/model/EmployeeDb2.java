package com.multipledbcon.db2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp_identity")
public class EmployeeDb2 {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "aadhar_no")
	private String aadhar_no;
	public EmployeeDb2() {
		// TODO Auto-generated constructor stub
	}
	public EmployeeDb2(Integer id, String aadhar_no) {
		super();
		this.id = id;
		this.aadhar_no = aadhar_no;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	
}
