import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {


    @Test
    public void testCalculateTotalCalories() {
        Pizza pizza = new Pizza(Map.of(
                Ingredients.MOZZARELLA, 200,
                Ingredients.TOMATO, 100
        ), true, "Test Pizza");

        assertEquals(Ingredients.MOZZARELLA.getCaloriesPer100g() * 200 / 100 +
                Ingredients.TOMATO.getCaloriesPer100g() * 100 / 100, pizza.calculateTotalCalories());
    }


    @Test
    public void testCalculateCaloriesPerSlice() {
        Pizza pizza = new Pizza(Map.of(
                Ingredients.MOZZARELLA, 200,
                Ingredients.TOMATO, 100
        ), true, "Test Pizza");

        int slices = 8;
        int totalCalories = pizza.calculateTotalCalories();
        assertEquals((float) totalCalories / slices * 2, pizza.calculateCaloriesPerSlice(2));
    }


    @Test
    public void testCheckIsVeganAndContainsTomatoAndPeppers() {
        Pizza veganPizza = new Pizza(Map.of(
                Ingredients.TOMATO, 100,
                Ingredients.PEPPERS, 50
        ), true, "Vegan Pizza");

        Pizza nonVeganPizza = new Pizza(Map.of(
                Ingredients.SALAMI, 200,
                Ingredients.MOZZARELLA, 150
        ), false, "Non-Vegan Pizza");

        List<Pizza> menu = List.of(veganPizza, nonVeganPizza);

        assertTrue(Menu.checkIsVeganAndContainsTomatoAndPeppers(menu));
    }

    @Test
    public void testCheckPizzaWithLowestCalories() {
        Pizza lowCaloriesPizza = new Pizza(Map.of(
                Ingredients.TOMATO, 80,
                Ingredients.PEPPERS, 50
        ), false, "Low calories Pizza");

        Pizza highCaloriesPizza = new Pizza(Map.of(
                Ingredients.SALAMI, 500,
                Ingredients.MOZZARELLA, 250
        ), false, "High calories Pizza");

        List<Pizza> menu = List.of(lowCaloriesPizza, highCaloriesPizza);

        Pizza pizzaWithLowestCalories = Menu.getPizzaWithLowestCalories(menu);

        assertEquals("Low calories Pizza", pizzaWithLowestCalories.name());

    }

    @Test
    public void testCheckPizzaWithHighCalories() {
        Pizza lowCaloriesPizza = new Pizza(Map.of(
                Ingredients.TOMATO, 80,
                Ingredients.PEPPERS, 50
        ), false, "Low calories Pizza");

        Pizza highCaloriesPizza = new Pizza(Map.of(
                Ingredients.SALAMI, 500,
                Ingredients.MOZZARELLA, 250
        ), false, "High calories Pizza");

        List<Pizza> menu = List.of(lowCaloriesPizza, highCaloriesPizza);

        Pizza pizzaWithHighestCalories = Menu.getPizzaWithHighestCalories(menu);

        assertEquals("High calories Pizza", pizzaWithHighestCalories.name());
    }

    @Test
    public void testDoesContainMozzarella() {
        Pizza pizzaWithMozzarella = new Pizza(Map.of(Ingredients.MOZZARELLA, 200), true, "Pizza with Mozzarella");
        Pizza pizzaWithoutMozzarella = new Pizza(Map.of(Ingredients.CELERY, 150), true, "Pizza without Mozzarella");

        assertTrue(Menu.doesContainMozzarella(List.of(pizzaWithMozzarella)));
        assertFalse(Menu.doesContainMozzarella(List.of(pizzaWithoutMozzarella)));
    }

}