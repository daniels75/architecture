package org.daniels.examples.hibernate.main;

import org.daniels.examples.hibernate.entities.Department;
import org.daniels.examples.hibernate.entities.Employee;
import org.daniels.examples.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EmployeeMain {
	
	private static final SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
	
	public static void main(String[] args) {

		try {
			final Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			Department department = new Department();
			department.setDepartmentName("Equites");
			session.save(department);

			Employee emp1 = new Employee();
			emp1.setFirstName("Daniel");
			emp1.setLastName("Sadowski");
			Employee emp2 = new Employee();
			emp2.setFirstName("Pawel");
			emp2.setLastName("Sadowski");

			emp1.setDepartment(department);
			emp2.setDepartment(department);

			session.save(emp1);
			session.save(emp2);

			session.getTransaction().commit();
			System.out.println(emp1);
		
		}
		finally {
			sessionFactory.close();
		}
	}
}
