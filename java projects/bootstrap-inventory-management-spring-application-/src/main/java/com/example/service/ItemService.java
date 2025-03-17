package com.example.service;

import java.util.List;

import com.example.entity.Item;
import com.example.exception.ImsException;

public interface  ItemService {
    Item add(Item item) throws ImsException;
    Item save(Item item) throws ImsException;
    boolean deleteItem(Integer icode) throws ImsException;
    Item getItemById(Integer icode) throws ImsException;
    List<Item> getAllItems() throws ImsException;
    public Item searchByTitle(String title) throws ImsException;
    public List<Item> searchByUnit(String unit) throws ImsException ;
    public List<Item> searchByPriceRange(double minPrice, double maxPrice) throws ImsException ;

}
