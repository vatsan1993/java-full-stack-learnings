package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Item;

@Controller
public class ItemController {
	
	@GetMapping("/items")
	public ModelAndView showItemForm() {
//		we are sending an empty item to the item form.
//		in the item-form-page we use modelAttribute="item"> to make use of this object 
//		each spring form tag will gave a path attribute which matches with the variables 
//		in the Item class.
		return new ModelAndView("item-form-page", "item", new Item());
	}

	@PostMapping("/items")
	public ModelAndView receiveItemForm(@ModelAttribute Item item) {
		return new ModelAndView("item-output-page", "item",item);
	}
	
//	This will help to send the categories to the item-form-page
//	when ever we use ${categories} in the form, this method is called and the categories are sent.
	@ModelAttribute("categories")
	public List<String> getCategories(){
		return Arrays.asList(new String[] {"Pulses", "Cerals", "Beverages", "Others"});
	}
	
}
