package com.example.ims.dao;

import java.util.List;

import com.example.ims.entity.Item;
import com.example.ims.exceptions.ImsException;

public interface ItemDao {

    public Item addItem(Item item) throws ImsException;
    public Item save(Item item) throws ImsException;
    public boolean deleteById(Integer icode) throws ImsException;

    public Item getById(Integer icode) throws ImsException;
    public List<Item> getAll() throws ImsException;

}
