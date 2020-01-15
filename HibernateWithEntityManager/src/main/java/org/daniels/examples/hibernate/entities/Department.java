package org.daniels.examples.hibernate.entities;

import com.google.common.collect.Lists;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue
    @Column(name="department_id")
    private Long departmentId;

    @Column(name="department_name")
    private String departmentName;

    @Column(name = "version")
    @Version
    private int version;

    @OneToMany(mappedBy="department",fetch = FetchType.EAGER)
    private List<Employee> employees = Lists.newArrayList();

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void add(Employee employee) {
        this.employees.add(employee);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", version=" + version +
                ", employees=" + employees +
                '}';
    }
}
