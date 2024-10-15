package day01;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {

    public static void main(String[] args) {

        //declare list
        List<String> cart = new LinkedList<>();

        Boolean stop = false;

        System.out.println("Welcome to your shopping cart!");

        //Get input from user
        Console cons = System.console();

        while (!stop) {

            // Read a line from console and trim it
            String cmd = cons.readLine(">").trim();
            String[] terms = cmd.split(" ");

            switch (terms[0].toLowerCase()) {

                case "add":
                // Add items to the cart
                if (terms.length > 1) {
                    for (int i = 1; i < terms.length; i++) {
                        cart.add(terms[i]);
                        System.out.println(terms[i] + " added to cart");
                        }
                    } else {
                        System.out.println("Please specify items to add");
                    }
                    break;

                case "delete":
                    int itemNumber = Integer.parseInt(terms[1]);
                    if (itemNumber > 0 && itemNumber <= cart.size()) {
                        cart.remove(itemNumber-1);
                    } else {
                        System.out.println("Error: Invalid number");
                    }

                    break;

                case "list":
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty");
                    } else {
                    System.out.println("Items in your cart:");
                    for (int i = 0; i < cart.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, cart.get(i));
                        }
                    }
                        break;
        
                case "exit":
                    stop = true;
                    System.out.println("Close");
                    break;

                default:
            }
        }
    }

}
