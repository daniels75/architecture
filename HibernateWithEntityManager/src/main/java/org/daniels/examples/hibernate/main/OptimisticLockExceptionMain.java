package org.daniels.examples.hibernate.main;

import org.daniels.examples.hibernate.entities.Employee;
import org.daniels.examples.hibernate.repository.EmployeeCrud;
import org.daniels.examples.hibernate.repository.EmployeeCrudImpl;
import org.daniels.examples.hibernate.util.HibernateUtil;

public class OptimisticLockExceptionMain {


    public static void main(String args[]) {
        EmployeeCrud employeeCrud = new EmployeeCrudImpl();

        try {
            Employee employee = new Employee();
            employee.setFirstName("Roland1");
            employee.setLastName("Hamilton");
            employeeCrud.saveEmployee(employee);

            employee.setFirstName("Roland2");
            employeeCrud.updateEmployee(employee);

            // Optimistic lock exception
            // here version is still not up-to-date
            // e.g. Roland3 update change version from 1->2
            // but here employee still contains "old" version 1
            // during an update javax.persistence.OptimisticLockException is throw
            employeeCrud.updateEmployee(employee);

        } finally {
            HibernateUtil.closeEntityManagerFactory();
        }
    }

    // SQL
    // SELECT * FROM hibernateexamples.employee;
    // DELETE FROM hibernateexamples.employee  where employee_id > 0;

}
