package com.cg.emp;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="dept1")
public class Department {
	
public Department(long id) {
		super();
		this.id = id;
	}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private long id;
@Column(name="deptname")
private String deptname;

@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<Employee> employees;

public Department() {
	super();
}

public Department(String deptname) {
	super();
	this.deptname = deptname;
}

public Department(long i, String string) {
	super();
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getDeptname() {
	return deptname;
}
public void setDeptname(String deptname) {
	this.deptname = deptname;
}

public Set<Employee> getEmployees() {
	return employees;
}

public void setEmployees(Set<Employee> employees) {
	this.employees = employees;
}
}