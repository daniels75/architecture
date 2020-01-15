package org.daniels.examples.hibernate.main;

import org.daniels.examples.hibernate.entities.Address;
import org.daniels.examples.hibernate.entities.Department;
import org.daniels.examples.hibernate.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerMain {

    private static final Logger logger = LoggerFactory.getLogger(EntityManagerMain.class);

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sample");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Department department = new Department();
        department.setDepartmentName("Equites");
        entityManager.persist(department);

        Address address1 = new Address();
        address1.setCity("GDA");
        address1.setStreet("KingStreet");

        Address address2 = new Address();
        address2.setCity("GDA");
        address2.setStreet("HillStreet");

        Address address3 = new Address();
        address3.setCity("GDA");
        address3.setStreet("Mokwy");

        Employee emp1 = new Employee();
        emp1.setFirstName("Daniel");
        emp1.setLastName("Sadowski");
        emp1.add(address1);
        address1.setEmployee(emp1);
        emp1.add(address2);
        address2.setEmployee(emp1);

        Employee emp2 = new Employee();
        emp2.setFirstName("Pawel");
        emp2.setLastName("Sadowski");
        emp2.add(address3);
        address3.setEmployee(emp2);

        emp1.setDepartment(department);
        emp2.setDepartment(department);

        entityManager.persist(emp1);
        entityManager.persist(emp2);

        entityManager.persist(address1);
        entityManager.persist(address2);
        entityManager.persist(address3);

        entityManager.flush();
        entityManager.clear();

        Department foundDepartment = entityManager.find(Department.class, department.getDepartmentId());
        logger.info("Found department: " + foundDepartment.toString());


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();



    }
}
