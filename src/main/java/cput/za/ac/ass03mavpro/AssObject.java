package cput.za.ac.ass03mavpro;

import java.io.*;
import java.util.*;

/**
 *
 * @author Adecel Rusty Mabiala
 * Assignment3 maven project
 * 219197229
 */

public class AssObject {
    List <Customer> customers = new ArrayList<Customer>();
    List<Supplier> suppliers = new ArrayList<Supplier>();
    
    public void readSer() {
        try {
            FileInputStream fis = new FileInputStream("stakeholder.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
            
            while(true){
                Object object;
                try{
                    object = objectInputStream.readObject();
                    if(object.toString().startsWith("C")){
                        customers.add((Customer) object);
                    }
                    else{
                        suppliers.add((Supplier) object);
                    }  
                }
                catch (EOFException e) {
                    break;
                }  
            }
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
                       e.printStackTrace();
        }
       
        sorting();
        ReFormat();
        writeCust();
        System.out.println(customers);
        
        sortingSup();
        writeSup();
         
   }
     
    public void sorting() {
        Collections.sort(customers, new Comparator<Customer>(){
            public int compare(Customer s1, Customer s2){
                return s1.getStHolderId().compareTo(s2.getStHolderId());  
           }
       });
    }
     
    
    public void ReFormat() {
         
         customers.get(0).setDateOfBirth("24 Jan 1993    	28   ");
         customers.get(1).setDateOfBirth("18 May 1987    	34   ");
         customers.get(2).setDateOfBirth("27 Jan 1981    	40   ");
         customers.get(3).setDateOfBirth("27 Nov 1999    	21   ");
         customers.get(4).setDateOfBirth("27 Jan 2001    	20   ");
         customers.get(5).setDateOfBirth("16 Jul 1998    	22   ");
         
     }
     
     public void writeCust() {
        try{
            FileWriter file = new FileWriter("customerOutFile.txt");
            PrintWriter printfile = new PrintWriter(file);
            printfile.println("Sample customer text-file output: ");
            printfile.println("");
            printfile.println("================================ CUSTOMERS ===================================");
            printfile.println("ID   	        Name            	Surname   	  Date of birth    	Age  ");
            printfile.println("================================================================================");
            printfile.println("");
            
            for (Customer customers: customers) {
                printfile.println(customers.getStHolderId() + "           "+customers.getFirstName() + "              " + 
                        customers.getSurName() +"                "+ customers.getDateOfBirth() + System.lineSeparator());
            }
            
            int count = 0;
            for (Customer customers: customers) {
                if (customers.getCanRent()== true) {
                    count ++;
                }
            }
            printfile.println("Number of customers who can rent: "+ count);
            
            
            int count2 = 0;
            for (Customer customers: customers) {
                if (customers.getCanRent()== false) {
                    count2 ++;
                }
            }
            printfile.println("Number of customers who cannot rent: "+ count2);
            
            printfile.close();
            }
            catch (IOException e) {
                e.printStackTrace();
        }
    }
     
     
       
    public void sortingSup() {
        Collections.sort(suppliers, new Comparator<Supplier>(){
            public int compare(Supplier s1, Supplier s2){
                return s1.getName().compareTo(s2.getName());  
           }
       });
    }
    
    public void writeSup() {
        try{
            FileWriter file = new FileWriter("supplierOutFile.txt");
            PrintWriter printfile = new PrintWriter(file);
            printfile.println("Sample supplier text-file output:");
            printfile.println("");
            printfile.println("================================ SUPPLIERS ===================================");
            printfile.println("ID   	        Name            	Prod Type   	  Description     ");
            printfile.println("================================================================================");
            printfile.println("");
            
            for (Supplier suppliers: suppliers) {
                printfile.println(suppliers.getStHolderId() + "           "+suppliers.getName() + "              " + 
                        suppliers.getProductType() +"                "+ suppliers.getProductDescription() + System.lineSeparator());
            }
            
            printfile.close();
            }
            catch (IOException e) {
                e.printStackTrace();
        }
    }
     
     

     
}
