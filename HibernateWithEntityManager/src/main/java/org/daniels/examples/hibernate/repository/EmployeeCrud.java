package org.daniels.examples.hibernate.repository;

import org.daniels.examples.hibernate.entities.Employee;

import java.util.List;

public interface EmployeeCrud {

	List<Employee> findAllEmployee();

	Employee saveEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	void updateEmployee(Long id);
	void readEmployee(Long id);
}
