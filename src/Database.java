import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Database
{
    //attributes
    private Order orderObj; 
    private ArrayList<Restaurant> listOfAllRestaurants = new ArrayList<>();
	private ArrayList<Restaurant> restaurantsByLocation = new ArrayList<>();
    private ArrayList<Meal> listOfAllDishes = new ArrayList<>();
	private ArrayList<Meal> listOfSignatureDishes = new ArrayList<>();


    public Database(){}

   /**
    *This method reads data from restos.csv file and creates a list of all the restaurants
    *return : list of all restaurants in the file
    */
	public ArrayList<Restaurant> getAllRestaurants()
	{
		Restaurant restaurantObj;
		try
        { 
            BufferedReader csvReader = new BufferedReader(new FileReader("restos.csv"));
            String row = null;
            int i = 0;
        
            while((row = csvReader.readLine()) != null)
            {
                if(i > 0)
                {
                    String[] data = row.split(",");
                    restaurantObj = new Restaurant(data[0].trim(), data[7].trim());
					listOfAllRestaurants.add(restaurantObj);
                }
                i++;
            }

            csvReader.close();
        }
        catch (Exception e)
        {
            System.out.println("Error : " + e.toString());
        }
		
		return listOfAllRestaurants;
	}
   /**
	*This method get the list of all the restaurants that are within the provided location
	*return : restaurants list
	*/
	public ArrayList<Restaurant> getRestaurantsByLocation(String location)
	{
		ArrayList<Restaurant> listOfAllRestos = getAllRestaurants();
		for(Restaurant restaurant : listOfAllRestaurants)
		{
			//If the location entered is equal to the location of the current restaurant
			//then add that restaurant in the restaurantByLocatin list
			if(location.equals(restaurant.getLocation()))
				restaurantsByLocation.add(restaurant);
		}
		
		return restaurantsByLocation;
	}
  /**
   *This method fetches all the meals from the csv file and stores them in a list
   *return : all dishes/meals
   */
    public ArrayList<Meal> getAllMeals()
	{
    	Meal meal;
		try
        { 
            BufferedReader csvReader = new BufferedReader(new FileReader("restos.csv"));
            String row = null;
            int i = 0;
        
            while ((row = csvReader.readLine()) != null)
            {
                if(i > 0)
                {
                    String[] data = row.split(",");
                    int x = 1;
                    int y = 0;
					for(int j = 0; j < 3; j++)
					{
						meal = new Meal(data[0], data[j+x].trim(), data[y+2].trim());
						listOfAllDishes.add(meal);
						x++;
						y += 2;
					}
					
					//System.out.println(listOfAllDishes.get(i));
					
                }
                i++;
            }
            csvReader.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
		return listOfAllDishes;
		
	}
    
   /**
	*This method filters dishes based on the provided restaurant
	*return : list of signature dishes for that provided restaurant
	*@return : ArrayList<Meal>
	*/
    public ArrayList<Meal> getDishesByRestaurant(Restaurant restaurant)
    {
		ArrayList<Meal> listOfAllMeals = getAllMeals();
		
		for(Meal meal : listOfAllMeals)
		{
			//If the location entered is equal to the location of the current restaurant
			//then add that restaurant in the restaurantByLocatin list
			if(meal.getRestaurantName().equals(restaurant.getRestaurantName()))
				listOfSignatureDishes.add(meal);
		}
		//revisit
		///////////////////////////////
		listOfSignatureDishes.remove(8);
		listOfSignatureDishes.remove(7);
		listOfSignatureDishes.remove(6);
		listOfSignatureDishes.remove(5);
		listOfSignatureDishes.remove(4);
		listOfSignatureDishes.remove(3);
		////////////////////////////////
		return listOfSignatureDishes;
       
    }
	public void writeOrdersIntoFile(Order orderObj)
	{
		try
		{
//			File file = new File("orders.csv");
//			PrintWriter output = new PrintWriter(file);
//			output.println(orderObj+"\r\n");
//			output.append(orderObj+"\n");
//			output.flush();
//			output.close();
			File file = new File("orders.csv");
			file.createNewFile();
			Writer output = new FileWriter(file, true);
			output.write(orderObj+"\n");
			output.flush();
			output.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error writing to the file");
		}
	}
	/**
	 * This method gets a list of orders from the orders.csv file based on the restaurant name provided
	 * @param restaurantName
	 * @return : list of orders by restaurant
	 */
	public ArrayList<String> getOrdersByRestaurant(String restaurantName)
    {
    	ArrayList<String> ordersByRestaurant = new ArrayList<>();
    	String orderDetails = "";
		try
        { 
            BufferedReader csvReader = new BufferedReader(new FileReader("orders.csv"));
            String row = null;
            int i = 0;
            int j = 1;
            while((row = csvReader.readLine()) != null)
            {
               if(i > 0)
                {
                	orderDetails = row;
                	String[] data = row.split(",");
                	if(data[0].equals(restaurantName))
                	{
                		ordersByRestaurant.add(j+". "+orderDetails); j++;
                	}
                }
                i++;
            }
            csvReader.close();
        }
        catch (Exception e)
        {
            System.out.println("Error : " + e.toString());
        }
		
		return ordersByRestaurant;
    }
    
}