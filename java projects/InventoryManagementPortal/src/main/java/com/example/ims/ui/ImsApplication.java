package com.example.ims.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.ims.entity.Item;
import com.example.ims.exceptions.ImsException;
import com.example.ims.service.ItemService;
import com.example.ims.service.ItemServiceImpl;

public class ImsApplication {
    public static final Scanner sc = new Scanner(System.in);
    public static final ItemService itemService = new ItemServiceImpl();



    public static void main(String[] args) {
        Menu menu = null;
        while(menu != Menu.QUIT) {
            System.out.println("Choice\tOperation");
            for(Menu m : Menu.values()) {
                // orinal() returns the index of the enum constant
                // m is the name of the enum constant
                System.out.println(m.ordinal() + "\t" + m);
            }
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            if(choice < 0 || choice >= Menu.values().length) {
                System.out.println("Invalid choice");
                continue;
            }
            menu = Menu.values()[choice];
            switch(menu) {
                case ADD:
                    System.out.println("Add Item");
                    doAddItem();

                    break;
                case DELETE:
                    System.out.println("Delete Item");
                    doDeleteItem();
                    break;
                case SEARCH:
                    System.out.println("Search Item");
                    doSearchItem();
                    break;
                case UPDATE:
                    System.out.println("Update Item");
                    doUpdateItem();
                    break;
                case LIST:
                    System.out.println("List Items");
                    doListItems();
                    break;
                case QUIT:
                    System.out.println("Quitting");
                    break;
            }
        }
    }


    private static void doAddItem() {
        // TODO Auto-generated method stub
        Item item = new Item();
        System.out.println("Enter the item id: ");
        item.setIcode(sc.nextInt());
        System.out.println("Enter the item name: ");
        item.setTitle(sc.next());
        System.out.println("Enter the unit: ");
        item.setUnit(sc.next());
        System.out.println("Enter the package date*(yyyy-MM-dd): ");
        item.setPackageDate(LocalDate.parse(sc.next()));
        System.out.println("Enter the fragility(true/false): ");
        item.setFragile(sc.nextBoolean());
        System.out.println("Enter the cost price: ");
        item.setCostPrice(sc.nextDouble());
        System.out.println("Enter the selling price: ");
        item.setSellingPrice(sc.nextDouble());
        try {
            itemService.validateAndAdd(item);
            System.out.println("Added successfully");
        } catch (ImsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void doDeleteItem() {

        System.out.println("Enter the item id to delete: ");
        int id = sc.nextInt();
        try {
            boolean deleted = itemService.deleteItem(id);
            if(deleted) {
                System.out.println("Item deleted successfully");
            } else {
                System.out.println("Item not found");
            }
        } catch (ImsException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void doSearchItem() {
        System.out.println("Enter the item id to search: ");
        int id = sc.nextInt();
        try {
            Item item = itemService.getItemById(id);
            if(item != null) {
                System.out.println(item);
            } else {
                System.out.println("Item not found");
            }
        } catch (ImsException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void doUpdateItem() {
        // TODO Auto-generated method stub
        Item item = new Item();
        System.out.println("Enter the item id: ");
        item.setIcode(sc.nextInt());
        System.out.println("Enter the new item name: ");
        item.setTitle(sc.next());
        System.out.println("Enter the new unit: ");
        item.setUnit(sc.next());
        System.out.println("Enter the new package date*(yyyy-MM-dd): ");
        item.setPackageDate(LocalDate.parse(sc.next()));
        System.out.println("Enter the new fragility(true/false): ");
        item.setFragile(sc.nextBoolean());
        System.out.println("Enter the new cost price: ");
        item.setCostPrice(sc.nextDouble());
        System.out.println("Enter the new selling price: ");
        item.setSellingPrice(sc.nextDouble());
        try {
            itemService.validateAndSave(item);
            System.out.println("Updated successfully");
        } catch (ImsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void doListItems()  {
        try {
            List<Item> items =itemService.getAllItems();
            if(items.isEmpty()) {
                System.out.println("No items to display");
            } else {
                for(Item item : items) {
                    System.out.println(item);
                }
            }
        } catch (ImsException e) {
            System.out.println(e.getMessage());
        }
    }

}
