package com.example.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.example.dao.ItemRepository;
import com.example.entity.Item;

@ActiveProfiles("test")
@DataJpaTest
public class ItemRepoTest {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private List<Item> testData;
	
	@BeforeEach
	public void setUp() {
		testData = Arrays.asList(
				new Item[] {
						new Item(101, "Rice Bag", LocalDate.now(), true, "BAG", 1024.0, 2024.0),
						new Item(102, "Sugar Bag", LocalDate.now(), true, "BAG", 3024.0, 4024.0),
						new Item(103, "Channa Bag", LocalDate.now(), true, "PACKET", 1024.0, 2024.0),
						new Item(104, "Wheat Bag", LocalDate.now(), true, "BAG", 5024.0, 7024.0),
						new Item(105, "Urdh Bag", LocalDate.now(), true, "PACKET", 204.0, 524.0)
				}
		);
		for(Item item: testData) {
			entityManager.persist(item);
		}
	}
	
	
	@AfterEach
	public void tearDown() {
		entityManager.clear();
		entityManager.flush();
		testData = null;
	}
	@Test
	public void whenFoundByTitle_GivenExistingTitle_test() {
		Item expected = testData.get(0);
		Item actual = itemRepository.findByTitle(expected.getTitle());
		
		assertEquals(expected, actual);
	}
	@Test
	public void whenFoundByTitle_GivenNonExistingTitle_test() {
		
		Item actual = itemRepository.findByTitle("asdjfkjsnfksjn");
		
		assertNull( actual);
	}
	
}