import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuLoader {
    public static List<Pizza> loadMenuFromTxt(String filePath) {
        List<Pizza> menu = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] pizzaData = line.split(",");

                if (pizzaData.length >= 3) {
                    String name = pizzaData[0].trim();
                    boolean isVegan = Boolean.parseBoolean(pizzaData[1].trim());

                    String[] ingredientsArray = pizzaData[2].split("\\|");
                    Map<Ingredients, Integer> ingredientsWithWeight = new HashMap<>();
                    for (String ingredientWithWeight : ingredientsArray) {
                        String[] parts = ingredientWithWeight.trim().split(" ");
                        Ingredients ingredient = Ingredients.valueOf(parts[0]);
                        int weight = Integer.parseInt(parts[1]);
                        ingredientsWithWeight.put(ingredient, weight);
                    }

                    Pizza pizza = new Pizza(ingredientsWithWeight, isVegan, name);
                    menu.add(pizza);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return menu;
    }
}