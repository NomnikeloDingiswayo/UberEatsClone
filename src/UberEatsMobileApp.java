import java.util.*;

/**
*Date : 12/08/2020
*@author: Asive Sifingo, Nomnikelo, Bandile Danxa 
*/
public class UberEatsMobileApp
{
    private static Scanner input = new Scanner(System.in);
	private static Order orderObj;
	private static Database databaseObj = new Database();
	private static ArrayList<Restaurant> restosInTheLocation = new ArrayList<>();
	private static ArrayList<Meal> dishByRestaurant = new ArrayList<>();
    private static ArrayList<Meal> itemsOrdered = new ArrayList<>();
	private static String location;
	private static String restaurantChoice;
	private static String option = "";//option for either adding or removing items on the cart
	private static int totalPrice = 0;
	
    public static void main(String[] args)
	{
    	Scanner input2 = new Scanner(System.in);
        System.out.println("Welcome to UberEatsApp - signature dish edition\n");

        System.out.println("Select location to load restaurants. Options are Rondebosch, Kenilworth, Seapoint or all\n");
        //takes user input
		location = input.nextLine().toLowerCase();

        System.out.println("Loading restaurants in your area...\n");
        //load list of restaurants
		restosInTheLocation = databaseObj.getRestaurantsByLocation(location);
		
		if(restosInTheLocation.isEmpty())
			System.out.println("There are no restaurants nearby the area provided");
		
		else
		{
			displayRestaurantNames();

	        System.out.println("Select restaurant number (eg '1' for KFC)...\n");
	        //takes user input and assign that as a restaurant choice
			restaurantChoice = input.nextLine();
			dishByRestaurant = databaseObj.getDishesByRestaurant(restaurant(restaurantChoice));
			
			String saveOption = "";
	        System.out.println("Loading dishes from your selected restaurant...\n");
			while(!saveOption.equals("Y"))
			{
				//Can add as many dishes until user types chooses to checkout to payment
				dislayDishMenu(dishByRestaurant);//load dishes of restaurant
				
				if(itemsOrdered.isEmpty())
					System.out.println("Your cart is currently empty\n");//display this only when the cart is empty
				
				System.out.println("Press 'a dishNumber' to add, and dishnumber is index+1 from the arraylist that its being printed from...\n");
				//Add items to cart
			    //When item is added to cart, cart is displayed as an array list showing items added to cart
				option = input.nextLine();
				addItemsOnCart(option);
				
				System.out.println("\nPress 'd+index' to delete item from cart...\n");
				//User can click on d+(index) to delete a dish from the cart
				option = input.nextLine().toLowerCase();
				if(!option.isEmpty())
				{
					if(option.substring(0, 1).equals("d"))
						removeItemsFromCart(option);
				}
				//Display items that are currently on the cart
				System.out.println(orderObj);
				
				System.out.println("Please confirm your order by pressing y for yes\n");
				saveOption = input2.nextLine().toUpperCase();
				//When y is pressed, Order gets added to CSV
				//Load items from cart
				if(saveOption.equals("Y"))
				{
					databaseObj.writeOrdersIntoFile(orderObj);
					//Traverse through the list of items ordered and calculate their final cost
					for(int k = 0; k < itemsOrdered.size(); k++)
						totalPrice += Integer.parseInt(orderObj.getItemsOrdered().get(k).getPrice());
				}
			}
	        
	        System.out.println("Cost of Order: R" +totalPrice);

	        System.out.println("Order has been placed! Thank you for your time. Restaurant will process your order soon!\n");

		}
		         
    }
   /**
	*This method displays a list of restaurants available based on the location provided
	*/
    static void displayRestaurantNames()
	{
		int x = 1;
		for(Restaurant restaurant : restosInTheLocation)
		{
			System.out.println(x+". "+restaurant.getRestaurantName()); x++;
		}
	}
   /**
	*This method processes the choice of the restaurant based on the selection number made by the user
	*@return : Restaurant that matches the choice number
	*/
	static Restaurant restaurant(String restaurantChoice)
	{
		switch(restaurantChoice)
		{
			case "1":
				return restosInTheLocation.get(0);
			case "2":
				return restosInTheLocation.get(1);
			case "3":
				return restosInTheLocation.get(2);
		}
		return restosInTheLocation.get(0);
	}
   /**
	*This method displays the dish menu based on the user's restaurant of choice
	*/
	static void dislayDishMenu(ArrayList<Meal> signatureDishes)
	{
		int x = 1;
		for(Meal dish : signatureDishes)
		{
			System.out.println(x+". "+dish); x++;
		}
	}
	/**
	 * This method adds items on the list of items ordered(Meal/Dish) and then take those items
	 * and pass them on orderList to update what is currently is current on the order list 
	 * @param option
	 */
	static void addItemsOnCart(String option)
	{
		switch(option)
		{
			case "1":
				itemsOrdered.add(new Meal(restaurant(restaurantChoice).getRestaurantName(), dishByRestaurant.get(0).getDishName(), dishByRestaurant.get(0).getPrice()));
				orderObj = new Order(restaurant(restaurantChoice).getRestaurantName(), itemsOrdered, location);
				break;
			case "2":
				itemsOrdered.add(new Meal(restaurant(restaurantChoice).getRestaurantName(), dishByRestaurant.get(1).getDishName(), dishByRestaurant.get(1).getPrice()));
				orderObj = new Order(restaurant(restaurantChoice).getRestaurantName(), itemsOrdered, location);
				break;
			case "3":
				itemsOrdered.add(new Meal(restaurant(restaurantChoice).getRestaurantName(), dishByRestaurant.get(2).getDishName(), dishByRestaurant.get(2).getPrice()));
				orderObj = new Order(restaurant(restaurantChoice).getRestaurantName(), itemsOrdered, location);
				break;
		}
	}
   /**
	* This method removes the item on the list of itemsOrdered based on the index provided
	* @param option
	*/
	static void removeItemsFromCart(String option)
	{
		String[] str = option.split("[+]");
		int index = Integer.parseInt(str[1]);
		itemsOrdered.remove(index);
	}
	
}