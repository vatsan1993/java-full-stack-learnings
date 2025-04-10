package com.example.test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.dao.ItemRepository;
import com.example.entity.Item;
import com.example.exception.ImsException;
import com.example.service.ItemService;


@ActiveProfiles("test")
@SpringBootTest
public class ItemServiceIntegrationTest {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	ItemService itemService;
	
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
			itemRepo.saveAndFlush(item);
			
		}

	}
	
	
	@AfterEach
	public void tearDown() {
		itemRepo.deleteAll();
		testData = null;
	}
	
	@Test
	public void whenAdd_givenNonExistingRecord_test() {
		Item item = new Item(106, "Urdh Bag", LocalDate.now(), true, "PACKET", 204.0, 524.0);
		
		
		try {
			assertEquals(itemService.add(item), item);
		} catch (ImsException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}
	
	@Test
	public void whenAdd_givenExistingRecord_test() {
		Item item = testData.get(0);
		
		
		assertThrows(ImsException.class, () -> {itemService.add(item);});
	}
	
	@Test
	public void whenGetItemById_givenExistingIcode_test() throws ImsException {
		Item expectedItem = testData.get(0);
		Item actualItem = itemService.getItemById(expectedItem.getIcode());
		System.out.println("Actual Item: "+actualItem);
		assertEquals(actualItem.getIcode(), expectedItem.getIcode());
	}
	
}
