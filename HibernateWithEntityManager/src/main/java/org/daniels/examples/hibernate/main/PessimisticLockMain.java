package org.daniels.examples.hibernate.main;

import org.daniels.examples.hibernate.entities.Employee;
import org.daniels.examples.hibernate.repository.EmployeeCrud;
import org.daniels.examples.hibernate.repository.EmployeeCrudImpl;
import org.daniels.examples.hibernate.util.HibernateUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PessimisticLockMain {
    public static void main(String args[]) {

        EmployeeCrud employeeCrud = new EmployeeCrudImpl();

        try {

            Employee employee = new Employee();
            employee.setFirstName("Roland");
            employee.setLastName("Anderson");
            final Employee savedEmployee = employeeCrud.saveEmployee(employee);
            System.out.println("Created employee: " + employee);

            System.out.println("-----------------------------------------------------");
            ExecutorService es = Executors.newFixedThreadPool(3);
            es.execute(() -> {
                employeeCrud.updateEmployee(savedEmployee.getEmployeeId());
            });
            es.execute(() -> {
                employeeCrud.readEmployee(savedEmployee.getEmployeeId());
            });
            es.shutdown();

            es.awaitTermination(20, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("-----------------------------------------------------");
            HibernateUtil.closeEntityManagerFactory();
        }
    }
}
