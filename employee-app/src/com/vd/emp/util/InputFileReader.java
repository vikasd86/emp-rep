package com.vd.emp.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vd.emp.vo.Employee;

/**
 * This class reads the input and store it in a data structure.
 * @author Vikas Dewan
 *
 */
public class InputFileReader 
{

	public static List<Employee> employeeList;
	
	static
	{
		employeeList = readEmployeesFromFile();
	}
	
	/**
	 * This method reads the input text file and populates the list of employees.
	 * @return
	 */
	private static List<Employee> readEmployeesFromFile() 
	{
		List<Employee> employees = new ArrayList<>();
		String fileName = "src/resource/input.txt";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			String line;

			while ((line = bufferedReader.readLine()) != null) 
			{
				String[] tempArr = line.split(",");

				long id = Long.parseLong(tempArr[0]);
				long managerId = 0;
				String name = tempArr[1];
				
				//check if manager id is null
				if(tempArr.length == 3)
				{
					managerId = Long.parseLong(tempArr[2]);
				}
				
				Employee employee = new Employee(id, managerId, name);
				employees.add(employee);
			}

		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found exception." + fnfe);
		} catch (IOException e) {
			System.out.println("IO Exception has occured " + e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if(fileReader != null) {
					fileReader.close();
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
		return employees;

	}
}
