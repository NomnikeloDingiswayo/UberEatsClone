import java.util.ArrayList;

public class Restaurant
{
	private String restaurantName;
	private String location;
	private ArrayList<Meal> signatureDishes = new ArrayList<>();
	

	public Restaurant(String restaurantName, String location)
	{
		this.restaurantName = restaurantName;
		this.location = location;
	}
	
	public void setRestaurantName(String restaurantName)
	{
		this.restaurantName = restaurantName;
	}
	public String getRestaurantName()
	{
		return restaurantName;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	public String getLocation()
	{
		return location;
	}
	public ArrayList<Meal> getSignatureDishes()
	{
		return signatureDishes;
	}

	public void setSignatureDishes(ArrayList<Meal> signatureDishes)
	{
		this.signatureDishes = signatureDishes;
	}
}