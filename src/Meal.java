
public class Meal
{
	private String dishName;
	private String price;
	private String restaurantName;

	public Meal(String restaurantName, String dishName, String price)
	{
		this.restaurantName = restaurantName;
		this.dishName = dishName;
		this.price = price;
	}
	public Meal(String dishName, String price)
	{
		this.dishName = dishName;
		this.price = price;
	}

	public String getDishName()
	{
		return dishName;
	}

	public void setDishName(String dishName)
	{
		this.dishName = dishName;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getRestaurantName()
	{
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName)
	{
		this.restaurantName = restaurantName;
	}
	
	@Override
	public String toString()
	{
		return dishName+" worth R"+price;
	}
}
