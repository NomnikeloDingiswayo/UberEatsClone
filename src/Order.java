import java.util.*;

public class Order
{
	//attributes
	private String restaurantName;
	private ArrayList<Meal> itemsOrdered;//Order is composed of a list of meals/dishes
	private String location;
	
	//constructor
	public Order(String restaurantName, ArrayList<Meal> itemsOrdered, String location)
	{
		this.restaurantName = restaurantName;
	    this.itemsOrdered = itemsOrdered;
		this.location = location;
	}
	
	//setter methods
	public void setLocation(String location)
	{
	    this.location = location;
	}
	
	public void setItemsOrdered(ArrayList<Meal> itemsOrdered)
	{
	    this.itemsOrdered = itemsOrdered;
	}
	
	public void setRestaurantName(String restaurantName)
	{
	    this.restaurantName = restaurantName;
	}
	
	//getter methods
	public String getLocation()
	{
	    return location; 
	}
   /**
	* 
	*@return : List of meals/dishes ordered
	\*/
    public ArrayList<Meal> getItemsOrdered()
	{
        return itemsOrdered;
	}
	
	public String getRestaurantName()
	{
	    return restaurantName;
	}
	
	@Override
	public String toString()
	{
	   return restaurantName +", "+ itemsOrdered +", "+ location;
	}
    
}