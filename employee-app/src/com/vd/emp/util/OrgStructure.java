package com.vd.emp.util;

import java.util.ArrayList;
import java.util.List;

import com.vd.emp.vo.Employee;

/**
 * This class contains the methods to create and display organization hierarchy.
 * @author Vikas Dewan
 *
 */
public class OrgStructure {

	private static List<Employee> employeeList = InputFileReader.employeeList;
	
	/**
	 * This method displays the organization hierarchy.
	 */
	public static void displayHierarchy() 
	{
		Employee cEO = findCEO();
		populateOrgChart(cEO);
		printOrgHierarchy(cEO, 0);
		displayEmployeeWithInvalidManagers();
	}

	/**
	 * This method finds the employee whose manager id is 0.
	 * @return Employee with 0 manager id
	 */
	private static Employee findCEO() 
	{
		Employee cEO = null;
		
		for (Employee employee : employeeList) {
			if (employee.getManagerId() == 0) {
				cEO = employee;
				break;
			}
		}
		return cEO;
	}

	/**
	 * This method populates the organization chart. It starts with root employee and find its all
	 * subordinates and then find subordinates of its subordinates and so on.
	 * @param manager
	 */
	private static void populateOrgChart(Employee manager) 
	{
		List<Employee> subordinates = findSubordinates(manager.getId());
		manager.setSubordinates(subordinates);

		for (Employee mgr : subordinates) {
			populateOrgChart(mgr);
		}

		return;
	}
	
	/**
	 * This method find subordinates of a particular employee.
	 * @param managerId
	 * @return
	 */
	private static List<Employee> findSubordinates(long managerId) 
	{
		List<Employee> subordinates = new ArrayList<>();

		for (Employee employee : employeeList) {
			if (employee.getManagerId() == managerId) {
				subordinates.add(employee);
			}
		}
		return subordinates;
	}
	
	/**
	 * This method prints the organization hierarchy on the screen.
	 * @param manager
	 * @param orgLevel
	 */
	private static void printOrgHierarchy(Employee manager, int orgLevel) {
		
		for(int iter=0; iter < orgLevel ; iter++)
		{
			System.out.print("  ");
		}
		
		System.out.println(manager.getName());

		for (Employee subordinate : manager.getSubordinates()) {
			printOrgHierarchy(subordinate, orgLevel + 1);
		}
	}
	
	/**
	 * This method displays the employee with ghost managers.
	 */
	private static void displayEmployeeWithInvalidManagers()
	{
		System.out.println();
		System.out.println(" ***Employee with Invalid Managers***");
		
		for(Employee employee : employeeList)
		{
			long managerId = employee.getManagerId();
			if(managerId != 0 && validEmployee(managerId) == false)
			{
				System.out.println(employee.getName());
			}
		}
	}
	
	/**
	 * This method checks whether employee is a valid one.
	 * @param employeeId : id of the employee
	 * @return true when employee is valid otherwise false.
	 */
	private static boolean validEmployee(long employeeId)
	{
		boolean isValidEmployee = false;
		
		for(Employee employee : employeeList)
		{
			if(employee.getId() == employeeId)
			{
				isValidEmployee = true;
				break;
			}
		}
		return isValidEmployee;
	}
}
