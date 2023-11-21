import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    private static final Map<String, Double> menuItemsAndPrice = new HashMap<>();

    public RestaurantService(){
        menuItemsAndPrice.put("Sweet Corn Soup", 5.0);
        menuItemsAndPrice.put("Vegetable Lasagne", 15.0);
        menuItemsAndPrice.put("Chicken Biriyani", 50.0);
    }

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                return restaurant;
            }
        }

        throw new restaurantNotFoundException("Restaurant " + restaurantName + " not found");
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Double calculateOrderValue(String... menuItems){

        Double totalCost = 0.0;

        for(String item : menuItems){
            if(menuItemsAndPrice.containsKey(item)){
                totalCost += menuItemsAndPrice.get(item);
            }
        }
        return totalCost;
    }
}
