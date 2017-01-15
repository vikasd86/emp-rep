package com.vd.emp.vo;

import java.util.List;

/**
 * This class is a pojo for Employee. 
 *
 */
public class Employee {

	private long id;
	private long managerId;
	private String name;
	private List<Employee> subordinates;
	
	public Employee(long id, long managerId, String name) {
		super();
		this.id = id;
		this.managerId = managerId;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Employee> getSubordinates() {
		return subordinates;
	}
	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", managerId=" + managerId + ", name=" + name + ", subordinates=" + subordinates
				+ "]";
	}
}
