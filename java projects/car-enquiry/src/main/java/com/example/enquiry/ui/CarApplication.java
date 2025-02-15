package com.example.enquiry.ui;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

import com.example.enquiry.dao.EnquiryDao;
import com.example.enquiry.dao.EnquiryDaoJdbcImpl;
import com.example.enquiry.entity.Enquiry;
import com.example.enquiry.entity.Status;
import com.example.enquiry.entity.VehicleType;

public class CarApplication {
    private static EnquiryDao enquiryDao;
    private static Scanner sc = new Scanner(System.in);
    private static void doAddEnquiry() {
        System.out.println("Add Enquiry");
        // enquiry_id, customer_name, mobile, mobile, vehicle_type, budget_from, budget_to, enquiry_status
        System.out.println("Enter Enquiry Id: ");
        Integer enquiryId = sc.nextInt();
        System.out.println("Enter Customer Name: ");
        String customerName = sc.next();
        System.out.println("Enter Mobile: ");
        BigInteger mobile = sc.nextBigInteger();
        System.out.println("Enter Vehicle Type: ");
        String vehicleType = sc.next();
        System.out.println("Enter Budget From: ");
        VehicleType entryVehicleType = null;
        try {
            entryVehicleType = VehicleType.valueOf(vehicleType);
        } catch (Exception e) {
            System.out.println("Invalid Vehicle Type");
            return;
        }
        BigInteger budgetFrom = sc.nextBigInteger();
        System.out.println("Enter Budget To: ");
        BigInteger budgetTo = sc.nextBigInteger();
        System.out.println("Enter Enquiry Status: ");
        String enquiryStatus = sc.next();
        Status entryStatus = null;
        try {
            entryStatus = Status.valueOf(enquiryStatus);
        } catch (Exception e) {
            System.out.println("Invalid Enquiry Status");
        }
        Enquiry enquiry = new Enquiry(enquiryId, customerName, mobile, entryVehicleType, budgetFrom, budgetTo, entryStatus);
        try {
            enquiryDao.addEnquiry(enquiry);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to add enquiry");
        }
    }

    private static void doDeleteEntry() {
        System.out.println("Delete Enquiry");
        System.out.println("Enter Enquiry Id: ");
        Integer enquiryId = sc.nextInt();
        try {
            enquiryDao.deleteEnquiryById(enquiryId);
            System.out.println("Enquiry deleted successfully");
        } catch (Exception e) {
            System.out.println("Unable to delete enquiry");
        }
    }

    private static void doSearchByMobile() {
        System.out.println("Search Enquiry by Mobile");
        System.out.println("Enter Mobile: ");
        BigInteger mobile = sc.nextBigInteger();
        try {
            System.out.println(enquiryDao.getEnquiryByMobile(mobile));

        } catch (Exception e) {
            System.out.println("Unable to search enquiry");
        }
    }

    private static void doUpdateEntryStatus() {
        System.out.println("Update Enquiry Status");
        System.out.println("Enter Enquiry Id: ");
        Integer enquiryId = sc.nextInt();
        System.out.println("Enter Enquiry Status: ");
        String enquiryStatus = sc.next();
        Status entryStatus = null;
        try {
            entryStatus = Status.valueOf(enquiryStatus);
        } catch (Exception e) {
            System.out.println("Invalid Enquiry Status");
        }
        try {
            enquiryDao.updateEnquiryStatusById(enquiryId, entryStatus);
            System.out.println("Enquiry status updated successfully");
        } catch (Exception e) {
            System.out.println("Unable to update enquiry status");
        }
    }

    private static void doListEnquiries() {
        try {
            List<Enquiry> enquiries = enquiryDao.getAllEnquiries();
            for(Enquiry enquiry : enquiries) {
                System.out.println(enquiry);
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Unable to list enquiries");
        }
    }

    private static void doListClosedEnquiries() {

        try {
            List<Enquiry> enquiries = enquiryDao.getClosedEnquiries();
            for(Enquiry enquiry : enquiries) {
                System.out.println(enquiry);
            }
        } catch (Exception e) {
            System.out.println("Unable to list closed enquiries");
        }
    }

    public static void main(String[] args) {
        enquiryDao = new EnquiryDaoJdbcImpl();
        Scanner sc = new Scanner(System.in);
        Menu menu = null;
        while(menu != Menu.QUIT) {
            System.out.println("Choice\tOperation");
            for(Menu m : Menu.values()) {
                // orinal() returns the index of the enum constant
                // m is the name of the enum constant
                System.out.println(m.ordinal() + "\t" + m);
            }
            System.out.println();
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            if(choice < 0 || choice >= Menu.values().length) {
                System.out.println("Invalid choice");
                continue;
            }
            menu = Menu.values()[choice];
            switch(menu) {
                case ADD:
                    System.out.println("Add Enquiries");
                    doAddEnquiry();
                    break;
                case DELETE:
                    System.out.println("Delete Enquiries");
                    doDeleteEntry();
                    break;
                case SEARCH_BY_MOBILE:
                    System.out.println("Search Enquiries");
                    doSearchByMobile();
                    break;
                case UPDATE:
                    System.out.println("Update Enquiries");
                    doUpdateEntryStatus();
                    break;
                case LIST:
                    System.out.println("List Enquiries");
                    doListEnquiries();
                    break;
                case LIST_CLOSED:
                    System.out.println("List Closed Enquiries");
                    doListClosedEnquiries();
                    break;
                case QUIT:
                    System.out.println("Quitting");
                    break;
            }
            System.out.println();
        }
    }
}
