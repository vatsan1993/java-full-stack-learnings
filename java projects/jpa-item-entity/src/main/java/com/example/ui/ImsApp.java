package com.example.ui;

import java.time.LocalDate;
import java.util.List;

import com.example.entity.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ImsApp {
	public static void main(String[] args) {
//		createItems();
//		updateItemById();
//		getItemById();
//		getAllItems();
//		getItemWhereClause();
//		getItemParameters();
//		getNamedQuery();
		deleteLast();
		getAllItems();
	}
	
	public static void deleteLast() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transact = em.getTransaction();
		transact.begin();
		 Item item = em.find(Item.class, 110);
	        
	        if (item != null) {
	            em.remove(item);  // Now it's managed, so it can be removed
	        } else {
	            System.out.println("Item with ID 110 not found.");
	        }
		
		transact.commit();
		em.close();
	}
	
	public static void getNamedQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Item> itemQuery = em.createNamedQuery("ListItemsWithFragileFilter", Item.class);
		itemQuery.setParameter("fragile", true);
		
		List<Item> items = itemQuery.getResultList();
		for(Item item : items) {
			System.out.println(item);
		}
		
		em.close();
		
	}
	
	public static void getItemParameters() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		String qryText = "SELECT i from Item i where i.costPrice > :minCost";
		TypedQuery<Item> itemQuery = em.createQuery(qryText, Item.class);
		itemQuery.setParameter("minCost", 50);
		List<Item> items = itemQuery.getResultList();
		for(Item item : items) {
			System.out.println(item);
		}
		
		em.close();
	}
	
	public static void getItemWhereClause() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		String qryText = "SELECT i from Item i where i.costPrice > 50";
		TypedQuery<Item> itemQuery = em.createQuery(qryText, Item.class);
		List<Item> items = itemQuery.getResultList();
		for(Item item : items) {
			System.out.println(item);
		}
		
		em.close();
	}
	
	public static void getAllItems() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		
		String qryText = "SELECT i from Item i";
		TypedQuery<Item> itemQuery = em.createQuery(qryText,Item.class);
		List<Item> items = itemQuery.getResultList();
		for(Item item : items) {
			System.out.println(item);
		}
		em.close();
	}
	
	public static void updateItemById() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transact = em.getTransaction();
		transact.begin();
		Item item = new Item(103, "Pen", LocalDate.now(), false, "Piece", 15, 25);
		em.merge(item);
		transact.commit();
		em.close();
	}
	
	public static void getItemById() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		Item item = em.find(Item.class, 101);
		System.out.println(item);
		em.close();
	}
	
	public static void createItems() {
//		Integer icode, String title, LocalDate packageDate, 
//		Boolean fragile, String unit, Integer costPrice,
//		Integer sellingPrice
		Item[] items = {
				new Item(101, "Laptop", LocalDate.now(), true, "Piece", 200, 750),
				new Item(102, "Book", LocalDate.now(), false, "Piece", 50, 100),
				new Item(103, "Pen", LocalDate.now(), false, "Piece", 10, 20),
				new Item(104, "Lamp", LocalDate.now(), true, "Piece", 25, 100),
				new Item(105, "Mobile", LocalDate.now(), true, "Piece", 120, 699),
				new Item(106, "Keyboard", LocalDate.now(), false, "Piece", 50, 250),
				new Item(107, "Tablet", LocalDate.now(), true, "Piece", 150, 400),
				new Item(108, "Pen", LocalDate.now(), false, "Box of 10", 80, 195),
				new Item(109, "Lamp", LocalDate.now(), true, "Box of 10", 230, 1000),
				new Item(110, "Mouse", LocalDate.now(), false, "Box of 10", 310, 1150),
		};
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transact = em.getTransaction();
		transact.begin();
		
		for(Item item: items) {
			em.persist(item);
		}
		
		transact.commit();
		em.close();
	}
}
