package org.daniels.examples.hibernate.entities;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Employee.FIND_ALL, query = "SELECT e FROM Employee e order by e.firstName")
})
@Entity
@Table(name="EMPLOYEE")
public class Employee {

    public static final String FIND_ALL = "FIND_ALL_EMPLOYEES";
    public static final String DELETE_ALL = "DELETE_ALL_EMPLOYEES";

    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "version")
    @Version
    private int version;

    @OneToMany(mappedBy="employee")
    private List<Address> addresses = Lists.newArrayList();

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void add(Address address) {
        this.addresses.add(address);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Employee: " +
                " Id=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", version=" + version +
                '}';
    }
}
