/*
   DonutMain.java
   MIS 350 MS 3
   Azam Khan
   04/23/2023
   This is the pseudocode for the main method of our group project
*/

// import scanner
import java.util.Scanner;
import java.util.Date;

// main method
public class DonutMain {

// initialize variables
private static String[] menuItems = {"Chocolate Donut", "Jelly Donut", "Glazed Donut", "Bacon Donut", "Blueberry Donut", "Double Chocolate Donut"};
private static double[] donutPrices = {1.00, 1.50, 1.00, 2.00, 2.50, 2.00};
private static int[] order = new int[99];
private static int orderNumber = 0;
private static int numItems = 0;
private static double total = 0;
private static double dailyTotalTax = 0;
private static double totalCashRevenue = 0;
private static double totalCreditRevenue = 0;
private static double dailyTotalRevenue = 0;
private static String delivery = "";
private static String tenderType = "";

   // main method
   public static void main( String[] args ) {
   // scanner input new
   Scanner input = new Scanner(System.in);
   int start = 1;
      

   // Welcome to K&S Donuts! Prompt
   do {
      System.out.println();
      System.out.println("Welcome to K&S Donuts!");

      // Take user input to complete day or start new order
      System.out.print("Press 1 to start a new order.\nOtherwise, enter anything else to end the day: ");
      start = input.nextInt();

      // if input == 1 - begin new order process
      if (start == 1) {
            newOrder();
            printReceipt(total, order, numItems, menuItems, donutPrices, orderNumber, delivery);
            orderNumber++;
      }
     // loop as long as user selects 1 
   } while (start == 1);
      
      
   // begin end of day revenue process
   EndDayRevenue(totalCashRevenue, totalCreditRevenue, dailyTotalRevenue, dailyTotalTax);
   // end
   }
   
   // newOrder() method
   public static void newOrder() {
      Scanner input = new Scanner(System.in);      
      
      // initialize variables and reset all order items to 0
      for (int i = 0; i < order.length; i++) {
         order[i] = 0;
      }
      numItems = 0;
      total = 0;
      int choice;
      int deliveryChoice;
      
      // display menu and prompt user to select item  
      do {
         System.out.println();
         System.out.println("-------------- K&S Donuts --------------\n\n\t\t\t\t\t\tMenu");
         System.out.println();
         // display items as list along with item #
         for (int i = 0; i < menuItems.length; i++) {
            System.out.printf("Item #%d %-25s $%5.2f\n", i+1, menuItems[i], donutPrices[i]);
         }
         System.out.println();
         // prompt user to select item or to finish order
         System.out.println("Select an item (1-6).\nOtherwise, enter 0 to finish your order.");
         choice = input.nextInt();
         
         // if selection is valid, add to order
         if (choice >= 1 && choice <= 6) {
            // if order array has space, add selection to order and increase numItems by 1.
            if (numItems <= order.length) {
               order[numItems] = choice;
               numItems++; // 'pointer'
               total += donutPrices[choice - 1]; // add donut price to total
            } else {
                  System.out.println("Max order size reached.");
            }
            // else if selection is invalid, prompt user to select again
         }  else if (choice != 0) {
               System.out.println("Please enter a number between 1 and 6.");
               input.nextLine();
         }
        // prompt user until they select 0 
      } while (choice != 0);
      
      // prompt user to select delivery method    
      do {
         System.out.println("Is this order for 1. delivery? Or 2. pickup?");
         deliveryChoice = input.nextInt();
         
         //
         if (deliveryChoice == 1) {
            delivery = "Delivery";
         } else if (deliveryChoice == 2) {
            delivery = "Pickup";
         } else {
            System.out.println("Please select using 1 or 2.");
         }
        // prompt until valid selection is made 
      } while (deliveryChoice != 1 && deliveryChoice != 2);
      
   }
   // printReceipt method
   public static void printReceipt(double total, int [] order, int numItems, String [] menuItems, double [] donutPrices, int orderNumber, String delivery) {
      
      Scanner input = new Scanner(System.in);
      
      // initialize variables
      double taxRate = 0.06;
      double tax = total * taxRate;
      double grandTotal = total + tax;
      
      // display receipt + receipt header
      System.out.println("\nVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
      System.out.println("\t\t\t\tK&S DONUTS - RECEIPT");
      System.out.println("-------------------------------------------");

      System.out.println("Date: " + new Date()); // print date
      System.out.println("Order Number: " + orderNumber); // print order #
      System.out.printf("Order Type: %s\n", delivery); // print order type
      System.out.println();
      System.out.println("Items: "); // print array of items selected
      for (int i = 0; i < numItems; i++) {
         System.out.printf("   %d. %-30s $%5.2f\n", i+1, menuItems[order[i] - 1], donutPrices[order[i] - 1]);
      }
      System.out.printf("Total Items: %d\n", numItems); // print total items #
      System.out.printf("\t\t\t\t\t\t\t\t\tSubtotal: $%5.2f\n", total); // print subtotal
      System.out.printf("\t\t\t\t\t\t\t\t\t\t  Tax: $%5.2f\n", tax); // print tax
      System.out.println();
      System.out.printf("\t\t\t\t\t\t\t\t\t\tTotal: $%5.2f\n", grandTotal); // print purchase total
      System.out.println();
      System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV\n");
      
      // prompt to input tender type      
      do {
         System.out.print("Enter the tender type (cash/credit) for order: ");
         String tenderType = input.next(); // get tender type from user
         if (tenderType.equalsIgnoreCase("cash")) {
            totalCashRevenue += total; // add order amount to cash revenue total
            break;
         } else if (tenderType.equalsIgnoreCase("credit")) { // check if tender type is credit
            totalCreditRevenue += total; // add order amount to credit revenue total
            break;
         } else System.out.print("Please enter correct form of payment\n");
        // prompt until tender type is cash or credit 
      } while (!tenderType.equalsIgnoreCase("cash") && !tenderType.equalsIgnoreCase("credit"));
      
      // add current order tax to daily total
      dailyTotalTax += tax;
      
   }
   // end of day revenue method
   public static void EndDayRevenue(double totalCashRevenue, double totalCreditRevenue, double dailyTotal, double dailyTotalTax) {
      // end of day revenue header
      System.out.println();
      System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
      System.out.println("  \t\tK&S DONUTS - END OF DAY REPORT");
      System.out.println("-------------------------------------------");
      System.out.println("Date: " + new Date()); // print date
      System.out.println();
      System.out.printf("Daily total Cash Revenue: \t\t\t\t$%5.2f\n", totalCashRevenue); // print daily total Cash revenue
      System.out.printf("Daily total Credit Revenue: \t\t\t$%5.2f\n", totalCreditRevenue); // print daily total Credit revenue
      System.out.printf("Daily total revenue:  \t\t\t\t\t$%5.2f\n", totalCashRevenue + totalCreditRevenue); // print daily total revenue
      System.out.printf("Daily total tax collected:  \t\t\t$%5.2f\n", dailyTotalTax); // print daily total tax collected
      System.out.println();
      System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV\n");
   }
}