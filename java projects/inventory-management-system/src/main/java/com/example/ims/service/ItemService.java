package com.example.ims.service;

import java.util.List;

import com.example.ims.entity.Item;
import com.example.ims.exceptions.ImsException;

public interface  ItemService {
    Item validateAndAdd(Item item) throws ImsException;
    Item validateAndSave(Item item) throws ImsException;
    boolean deleteItem(Integer icode) throws ImsException;
    Item getItemById(Integer icode) throws ImsException;
    List<Item> getAllItems() throws ImsException;

}
