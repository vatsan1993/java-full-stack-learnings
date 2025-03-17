package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Item;
import com.example.exception.ImsException;
import com.example.service.ItemService;

import jakarta.validation.Valid;

@Controller
public class itemController {
	
	@Autowired
	private ItemService itemService;
	
	
	@GetMapping("/list")
	public ModelAndView shopItemsList() throws ImsException {
		// we will handle the exceptsions later
		return new ModelAndView("itemsListPage", "items", itemService.getAllItems());
	}
	
	@GetMapping("/newItem")
	public ModelAndView getItemForm() {
		
		ModelAndView mv =  new ModelAndView("ItemFormPage", "item", new Item());
		mv.addObject("isNew", true);
		// this makes sure we dont get any error after we click sumbit for the second time 
		return mv;
	}
	
	@PostMapping("/addItem")
	public ModelAndView addNewItem(@ModelAttribute("item") @Valid Item item, BindingResult result) throws ImsException {
//		@Valid  will validate the item and if any errors are there, the errors are stored in result
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("ItemFormPage", "item", item);
			mv.addObject("isNew", true);
			return mv;
		}
		itemService.add(item);
		return new ModelAndView("itemsListPage", "items", itemService.getAllItems());
	}

	@GetMapping("/editItem")
	public ModelAndView getEditItemForm(@RequestParam("icode") String icode) throws NumberFormatException, ImsException {
		Item item = itemService.getItemById(Integer.parseInt(icode));
		return new ModelAndView("ItemFormPage", "item", item);
	}
	
	@PostMapping("/saveItem")
	public ModelAndView saveItem(@ModelAttribute("item") @Valid Item item, BindingResult result) throws ImsException {
//		@Valid  will validate the item and if any errors are there, the errors are stored in result
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("ItemFormPage", "item", item);
		
			return mv;
		}
		itemService.save(item);
		return new ModelAndView("itemsListPage", "items", itemService.getAllItems());
	}

	
	@GetMapping("/deleteItem")
	public ModelAndView deleteItem(@RequestParam("icode") int icode) throws  ImsException {
		itemService.deleteItem(icode);
		return new ModelAndView("itemsListPage", "items", itemService.getAllItems());
	}
	
//	get the options for the select element.
	@ModelAttribute("units")
	public List<String> getUnits(){
		return Arrays.asList(new String[] {"Kg", "Mtr", "Ltr", "Packet","Piece"});
		
	}
	
	 @GetMapping("/search")
	    public ModelAndView showSearchForm() {
	        ModelAndView mv = new ModelAndView("searchFormPage");
	        mv.addObject("searchTypes", Arrays.asList("Title", "Unit", "Price Range"));
	        return mv;
	    }

	    @PostMapping("/searchResults")
	    public ModelAndView searchItems(@RequestParam("searchType") String searchType,
	                                    @RequestParam("searchValue") String searchValue) throws ImsException {
	        
	    	System.out.println("Searching.........");
	    	
	    	List<Item> results = null;

	        switch (searchType) {
	            case "Title":
	                results = Arrays.asList(itemService.searchByTitle(searchValue));
	                break;
	            case "Unit":
	                results = itemService.searchByUnit(searchValue);
	                break;
	            case "Price Range":
	                // Assuming input is like "min-max" (e.g., "100-500")
	                String[] range = searchValue.split("-");
	                if (range.length == 2) {
	                    double min = Double.parseDouble(range[0]);
	                    double max = Double.parseDouble(range[1]);
	                    results = itemService.searchByPriceRange(min, max);
	                } else {
	                    results = List.of(); // Empty result if input is invalid
	                }
	                break;
	            default:
	                results = List.of();
	        }

	        
	      
	        return new ModelAndView("itemsListPage", "items", results);
	        
	      
	    }

}
