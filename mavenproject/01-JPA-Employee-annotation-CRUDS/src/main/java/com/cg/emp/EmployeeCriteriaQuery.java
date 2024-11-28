package com.cg.emp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class EmployeeCriteriaQuery {

	EntityManager em;

	public EmployeeCriteriaQuery(EntityManager em) {
		super();
		this.em = em;
	}
	
	public List<Employee> listAllEmployeesUsingCriteriaQuery(){
		
		CriteriaBuilder criteriabuilder=em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = criteriabuilder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root);
		return em.createQuery(cq).getResultList();
	}
	
	public List<Employee> findEmployeeByName(String name){
		CriteriaBuilder criteriabuilder=em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = criteriabuilder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root).where(criteriabuilder.equal(root.get("firstName"), name));
		return em.createQuery(cq).getResultList();
		
	}
	public List<Employee> findEmployeeForGTSalary(String name){
		CriteriaBuilder criteriabuilder=em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = criteriabuilder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root).where(criteriabuilder.greaterThan(root.get("salary"), name));
		return em.createQuery(cq).getResultList();
		
	}
	
	public List<Employee> findEmployeeForLTSalary(String name){
		CriteriaBuilder criteriabuilder=em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = criteriabuilder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root).where(criteriabuilder.lessThan(root.get("salary"), name));
		return em.createQuery(cq).getResultList();
		
	}
	
	public List<Employee> findEmployeesByNameStartsWith(String prefix){
		CriteriaBuilder criteriabuilder=em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = criteriabuilder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root).where(criteriabuilder.like(root.get("firstName"), prefix + "%"));
		return em.createQuery(cq).getResultList();
	}
	public List<Employee> findEmployeesRange(int minSalary,int maxSalary){
		CriteriaBuilder criteriabuilder=em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = criteriabuilder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root).where(criteriabuilder.between(root.get("salary"), minSalary, maxSalary));
		return em.createQuery(cq).getResultList();
	}
	
	public List<Employee> findEmployeesAscOfSalary(){
		CriteriaBuilder criteriabuilder=em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = criteriabuilder.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root).orderBy(criteriabuilder.asc(root.get("salary")));
		return em.createQuery(cq).getResultList();
	}
	
	public List<Long> findEmployeesCount(){
		CriteriaBuilder criteriabuilder=em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = criteriabuilder.createQuery(Long.class);
		
		Root<Employee> root = cq.from(Employee.class);
		cq.select(criteriabuilder.count(root));
		return em.createQuery(cq).getResultList();
	}
	
	
}
