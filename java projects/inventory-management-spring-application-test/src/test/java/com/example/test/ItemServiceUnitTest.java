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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.dao.ItemRepository;
import com.example.entity.Item;
import com.example.exception.ImsException;
import com.example.service.ItemService;
import com.example.service.ItemServiceImpl;


@SpringJUnitConfig
public class ItemServiceUnitTest {
	
	@TestConfiguration
	static class TestComfigurationClass{
		@Bean
		public ItemService itemService() {
			return new ItemServiceImpl();
			
		}
	}
	
	@MockBean
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
		
	}
	
	
	@AfterEach
	public void tearDown() {
	
		testData = null;
	}
	
	@Test
	public void whenAdd_givenNonExistingRecord_test() {
		Item item = new Item(105, "Urdh Bag", LocalDate.now(), true, "PACKET", 204.0, 524.0);
		Mockito.when(itemRepo.existsById(106)).thenReturn(false);
		Mockito.when(itemRepo.save(item)).thenReturn(item);
		
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
		Mockito.when(itemRepo.existsById(item.getIcode())).thenReturn(true);
		Mockito.when(itemRepo.save(item)).thenReturn(item);
		
		assertThrows(ImsException.class, () -> {itemService.add(item);});
	}
	
	@Test
	public void whenGetItemById_givenExistingIcode_test() throws ImsException {
		Item item = testData.get(0);
		Mockito.when(itemRepo.findById(item.getIcode())).thenReturn(Optional.ofNullable(item));
		assertEquals(itemService.getItemById(item.getIcode()), item);
	}
	
}
