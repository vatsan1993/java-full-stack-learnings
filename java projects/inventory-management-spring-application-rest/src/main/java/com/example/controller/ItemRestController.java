package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Item;
import com.example.exception.ImsException;
import com.example.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemRestController {
	
	@Autowired
	private ItemService itemService;
	
//	if we dont provide any url the method will use the same url as its parent class.
	@GetMapping
	public ResponseEntity<List<Item>> getAllItems() throws ImsException{
		return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
	}
	
	@GetMapping("/{icode}")
	public ResponseEntity<Item> getItem(@PathVariable int icode) throws ImsException{
		ResponseEntity<Item> response= null;
		Item item = itemService.getItemById(icode);
		if(item != null)
			response = new ResponseEntity<>(item, HttpStatus.OK);
		else
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return response;
	}
	
	@DeleteMapping("/{icode}")
	public ResponseEntity<Void> deleteItem(@PathVariable int icode) throws ImsException{
		ResponseEntity<Void> response= null;
		boolean isDeleted = itemService.deleteItem(icode);
		if(isDeleted)
			response = new ResponseEntity<>(HttpStatus.OK);
		else
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return response;
	}
	
	@PostMapping
	public ResponseEntity<Item> createItem(@RequestBody @Valid Item item) throws ImsException{
		
		return new ResponseEntity<Item> (itemService.add(item), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Item> updateItem(@RequestBody @Valid Item item) throws ImsException{		
		return new ResponseEntity<Item> (itemService.save(item), HttpStatus.OK);
	}

}
