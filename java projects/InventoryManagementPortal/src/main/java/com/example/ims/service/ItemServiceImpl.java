package com.example.ims.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.ims.dao.ItemDao;
import com.example.ims.dao.ItemDaoJdbcImpl;
import com.example.ims.entity.Item;
import com.example.ims.exceptions.ImsException;

public class ItemServiceImpl implements ItemService {
    ItemDao itemDao;

    public ItemServiceImpl() {
        this.itemDao = new ItemDaoJdbcImpl();
    }

    private boolean isIcodeValid(Integer icode) {
        return icode != null && icode > 0;
    }

    private boolean isTitleValid(String title) {
        return title != null && title.length() >= 3 && title.length() <= 20;
    }

    private boolean isPackageDateValid(LocalDate packageDate) {
        LocalDate today = LocalDate.now();
        return packageDate != null && (today.isAfter(packageDate) || today.isEqual(packageDate));
    }

    private boolean isFragileValid(Boolean fragile) {
        return fragile != null;
    }

    private boolean isUnitValid(String unit) {
        return unit != null;
    }

    private boolean isPriceValid(Double price) {
        return price != null && price > 0;
    }

    private boolean isItemValid(Item item) throws ImsException {
        List<String> errMsgs = new ArrayList<>();
        boolean isValid = true;
        if(! isIcodeValid(item.getIcode()) ){
            isValid = false;
            errMsgs.add("icode cannot be null and must be a positive number");
        }

        if(! isTitleValid(item.getTitle()) ){
            isValid = false;
            errMsgs.add("title cannot be null and must be 3 to 20 chars long");
        }

        if(! isPackageDateValid(item.getPackageDate()) ){
            isValid = false;
            errMsgs.add("package date cannot be null and must be today or before");
        }

        if(! isFragileValid(item.getFragile()) ){
            isValid = false;
            errMsgs.add("fragile cannot be empty. It should be true or false");
        }

        if(! isUnitValid(item.getUnit()) ){
            isValid = false;
            errMsgs.add("unit cannot be null");
        }
        if(! isPriceValid(item.getCostPrice()) ){
            isValid = false;
            errMsgs.add("cost price cannot be null and must be a positive number");
        }
        if(! isPriceValid(item.getSellingPrice()) ){
            isValid = false;
            errMsgs.add("selling price cannot be null and must be a positive number");
        }
        if(! isValid){
            throw new ImsException(errMsgs.toString());
        }

        return isValid;
    }

    @Override
    public Item validateAndAdd(Item item) throws ImsException {
        if(item != null){
            if(isItemValid(item)){
                itemDao.addItem(item);
                return item;
            }
        }
        throw new ImsException("item is null or invalid");
    }

    @Override
    public Item validateAndSave(Item item) throws ImsException {
        if(item != null){
            if(isItemValid(item)){
                itemDao.save(item);
                return item;
            }
        }
        throw new ImsException("item is null or invalid");
    }

    @Override
    public boolean deleteItem(Integer icode) throws ImsException {
        if(icode != null){
            if(isIcodeValid(icode)){
                itemDao.deleteById(icode);
                return true;
            }
        }
        throw new ImsException("icode is null or invalid");
    }

    @Override
    public Item getItemById(Integer icode) throws ImsException {
        if(icode != null){
            if(isIcodeValid(icode)){
                Item item = itemDao.getById(icode);
                return item;
            }
        }
        throw new ImsException("icode is null or invalid");
    }

    @Override
    public List<Item> getAllItems() throws ImsException {
        return itemDao.getAll();
    }

}
