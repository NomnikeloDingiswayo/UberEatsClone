import java.util.*;

public class UberEatsRestaurantApp
{
	private static Scanner input = new Scanner(System.in);
	
    public static void main(String[] args)
    {
        System.out.println("Welcome to UberEatsApp - signature dish edition FOR RESTAURANTS\n");

        System.out.println("Enter restaurant name\n");
        //takes user input
        String restaurantName = input.nextLine();
        
        System.out.println("Loading all orders placed...\n");
        //load list of orders
        ArrayList<String> ordersByResto = new Database().getOrdersByRestaurant(restaurantName);
        
        //Display all the orders that are on the orders.csv file based on the restaurant name provided
        for(String order : ordersByResto)
        	System.out.println(order);
       
    }
}