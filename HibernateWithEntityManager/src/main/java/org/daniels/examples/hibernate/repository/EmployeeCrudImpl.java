package org.daniels.examples.hibernate.repository;

import org.daniels.examples.hibernate.entities.Employee;
import org.daniels.examples.hibernate.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EmployeeCrudImpl implements EmployeeCrud {

    public EmployeeCrudImpl() {
    }

    public Employee saveEmployee(Employee employee) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        EntityManager em = HibernateUtil.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return employee;
    }

    public List<Employee> findAllEmployee() {
        EntityManager em = HibernateUtil.createEntityManager();
        Query query = em.createNamedQuery(Employee.FIND_ALL);
        List<Employee> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public void updateEmployee(Long id) {
        // this not needed in fact
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.lock.timeout", 500L);

        System.out.println("[>WRITE] START");
        EntityManager em = HibernateUtil.createEntityManager();
        em.getTransaction().begin();
        System.out.println("[>WRITE] Before lock PESSIMISTIC_WRITE");

        // 1st approach with PESSIMISTIC_WRITE we have select for update and find
        // from readEmployee method cannot read a record from DB
        // until record is not committed/saved by following commit
        // 2nd approach - another behavior when PESSIMISTIC_WRITE -> PESSIMISTIC_READ then both can read the same record
        Employee employee = em.find(Employee.class, id, LockModeType.PESSIMISTIC_WRITE, properties);
        // SELECT * FROM EMPLOYEE WHERE id=? FOR UPDATE
        // can be also like:
        // Employee employee = em.find(Employee.class, id, properties);
        // em.lock(employee, LockModeType.PESSIMISTIC_WRITE);
        System.out.println("[>WRITE] employee: " + employee);
        System.out.println("[>WRITE] After lock PESSIMISTIC_WRITE");
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("[>WRITE] Waiting 3 second");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        employee.setFirstName("XY New Name");
        em.getTransaction().commit();
        System.out.println("[<WRITE] After lock PESSIMISTIC_WRITE");
        System.out.println("[>WRITE] employee: " + employee);
        System.out.println("[>WRITE] STOP");
    }

    @Override
    public void readEmployee(Long id) {
        System.out.println("[<READ] START");

        try {
            System.out.println("[>READ] Waiting 100 millisecond");
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EntityManager em = HibernateUtil.createEntityManager();
        em.getTransaction().begin();
        System.out.println("[<READ] Before lock PESSIMISTIC_READ");
        Employee employee = em.find(Employee.class, id, LockModeType.PESSIMISTIC_READ);
        // SELECT * FROM EMPLOYEE WHERE id=? LOCK IN SHARE MODE
        System.out.println("[<READ] employee: " + employee);
        em.getTransaction().commit();
        System.out.println("[<READ] After lock PESSIMISTIC_READ");
        System.out.println("[<READ]  STOP");
    }

}
