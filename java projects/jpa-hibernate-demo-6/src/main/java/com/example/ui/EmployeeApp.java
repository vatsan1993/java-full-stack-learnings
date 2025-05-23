package com.example.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.entity.Address;
import com.example.entity.BankAccount;
import com.example.entity.ContractEmployee;
import com.example.entity.Department;
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
//		createEmployees();
//		getEmployee();
//		getMultipleEmployees();
//		getEmployeesWhereClause();
//		getEmployeesWithParameters();
//		getEmployees();
		
//		retrieve dno, street, first name of the employee.
		getCompositionValues();
//		retrieve employeeFirstName,BankName and the Branch
		getOneOnOne();

		
		
		
		
	}
	
	
	
	public static void getOneOnOne() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		// transaction is better to create transaction if there are multiple insert or
		// update.
		EntityTransaction txn = em.getTransaction();
		// starting transaction
		txn.begin();
		
//		As this data will not have complete Employee details it We need Object[] 
//		and as there are multiple employees we need List
		
		final String QRY = "SELECT e.firstName, e.account.bank, e.account.branch from Employee e";
		List<Object[]> data1 =  em.createQuery(QRY).getResultList();
		
		for(Object[] row: data1) {
			System.out.println(row[0]+" "+row[1]+ " "+row[2]);
		}
	
		txn.commit();
		em.close();
	}
	
	public static void getCompositionValues() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		// transaction is better to create transaction if there are multiple insert or
		// update.
		EntityTransaction txn = em.getTransaction();
		// starting transaction
		txn.begin();
		
//		As this data will not have complete Employee details it We need Object[] 
//		and as there are multiple employees we need List
		
		final String QRY = "SELECT e.firstName, e.address.doorNumber, e.address.city from Employee e";
		List<Object[]> data1 =  em.createQuery(QRY).getResultList();
		
		for(Object[] row: data1) {
			System.out.println(row[0]+" "+row[1]+ " "+row[2]);
		}
	
		txn.commit();
		em.close();
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
		BankAccount account = new BankAccount(124232342L,"Vijayawada","SBI");
		
		// setting id to null as it will be auto generated.
		Department dept = new Department(null, "AI");
		
		emp.setAccount(account);
		account.setAccountHolder(emp);
		
		emp.setDepartment(dept);
		dept.addEmployee(emp);
		
		Address addr2 = new Address("103","baker Street", "NY");
		Employee emp2 = new Employee(102L, "max", "Miller", 25000.0, LocalDate.now(), addr2);
		BankAccount account2 = new BankAccount(124232343L,"Texas","SBI");
		
		emp2.setAccount(account2);
		account2.setAccountHolder(emp2);
		
		emp2.setDepartment(dept);
		dept.addEmployee(emp2);
		
		
		em.persist(emp);
		em.persist(emp2);
	
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
