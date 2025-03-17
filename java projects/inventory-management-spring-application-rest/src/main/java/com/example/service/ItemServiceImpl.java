package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ItemRepository;
import com.example.entity.Item;
import com.example.exception.ImsException;

import jakarta.transaction.Transactional;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemRepository itemRepo;
	
	@Override
	@Transactional // to manage transactions, we need to use this.
	public Item add(Item item) throws ImsException {
	
		if(item != null) {
			if(itemRepo.existsById(item.getIcode())) {
				throw new ImsException("Item code already used!");
			}
			itemRepo.save(item);
			return item;
		}
		return null;
	}

	@Transactional
	@Override
	public Item save(Item item) throws ImsException {
		if(item != null) {
			if(!itemRepo.existsById(item.getIcode())) {
				throw new ImsException("Item code already used!");
			}
			itemRepo.save(item);
			return item;
		}
		return null;
	}

	@Transactional
	@Override
	public boolean deleteItem(Integer icode) throws ImsException {
		// TODO Auto-generated method stub
		
		if(!itemRepo.existsById(icode)) {
			
			throw new ImsException("Item not found!");
		}
		itemRepo.deleteById(icode);
		return true;
	}

	@Transactional
	@Override
	public Item getItemById(Integer icode) throws ImsException {
		return itemRepo.findById(icode).orElse(null);
	}

	@Transactional
	@Override
	public List<Item> getAllItems() throws ImsException {
		return itemRepo.findAll();
	}

	 public Item searchByTitle(String title) throws ImsException{
	     return itemRepo.findByTitle(title);
	 }

    public List<Item> searchByUnit(String unit) throws ImsException {
        return itemRepo.findAllByUnit(unit);
    }

    public List<Item> searchByPriceRange(double minPrice, double maxPrice) throws ImsException {
        return itemRepo.findAllInSellingPriceRange(minPrice, maxPrice);
    }
	
}
