package com.example.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.entity.Address;
import com.example.entity.ContractEmployee;
import com.example.entity.Employee;
import com.example.entity.Manager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class EmployeeApp {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
//		createEmployees();s
//		getEmployee();
//		getMultipleEmployees();
//		getEmployeesWhereClause();
//		getEmployeesWithParameters();
		getEmployees();
	}

	public static void createEmployees() {
//			persistenceUnitName - mysqlPU
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		// transaction is better to create transaction if there are multiple insert or
		// update.
		EntityTransaction txn = em.getTransaction();
		// starting transaction
		txn.begin();
		Address addr1 = new Address("102","baker Street", "NY");
		
		Employee emp = new Employee(101L, "Vansy", "Aripaka", 25000.0, LocalDate.now(), addr1);
		Manager mgr = new Manager(102L, "Srinu", "Dachepalli", 55000.0, LocalDate.now(), addr1, 8525.0);
		ContractEmployee cemp = new ContractEmployee(103L, "Suseela", "Aripaka", 35000.0, LocalDate.now(),addr1, 12);

		em.persist(emp);
		em.persist(mgr);
		em.persist(cemp);
		txn.commit();
		em.close();
	}

	public static void getEmployees() {
//		persistenceUnitName - mysqlPU
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();

//		gets all the employees, manager, Contract emp
		final String eQry = "SELECT e from Employee e";
//		gets all the  managers
		final String mQry = "SELECT m from Manager m";
//		gets all contract employees
		final String ceQry = "SELECT ce from ContractEmployee ce";

		List<Employee> emps = em.createQuery(eQry, Employee.class).getResultList();
//		List<Manager> mgrs = em.createQuery(mQry,Manager.class).getResultList();
//		List<ContractEmployee> cemps = em.createQuery(ceQry,ContractEmployee.class).getResultList();
		for (Employee emp : emps) {
			System.out.println(emp);
		}
//		for(Manager emp : mgrs) {
//			System.out.println(emp);
//		}
//		for(ContractEmployee emp : cemps) {
//			System.out.println(emp);
//		}
		em.close();
	}
}
