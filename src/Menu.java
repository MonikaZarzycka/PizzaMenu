import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Menu {
    public static void main(String[] args) {

        List<Pizza> menu = MenuLoader.loadMenuFromTxt("C:\\Users\\misia\\IdeaProjects\\POII\\OblicznieKaloriiPizzy\\src\\resources\\menu.txt");

        System.out.println("Calorie list:");
        printSliceCalories(menu, 8);
        printCalories(menu);
        System.out.println();
        System.out.println("List of vegan pizzas:");
        printVegan(menu);
        System.out.println();
        System.out.println("Pizza with allergens - celery: ");
        printContainsCelery(menu);
        System.out.println();
        System.out.println("We have pizzas with tomato and pepper: " + checkIsVeganAndContainsTomatoAndPeppers(menu));
        System.out.println();
        System.out.println("Whether all dishes contain mozzarella? " + doesContainMozzarella(menu));
        System.out.println();
        getPizzaWithHighestCalories(menu);
        System.out.println();
        Pizza pizzaWithLowestCalories = getPizzaWithLowestCalories(menu);
        System.out.println("Pizza with lowest calories is: " + pizzaWithLowestCalories.name() +
                " with " + pizzaWithLowestCalories.calculateTotalCalories() + " calories");
    }


    public static void printSliceCalories(List<Pizza> menu, int slices) {
        Stream<Pizza> pizzaStream = menu.stream();
        pizzaStream.forEach(pizza ->
                System.out.println("Total calories in " + pizza.name() +
                        " (" + slices + " slices): " + pizza.calculateCaloriesPerSlice(slices)));
    }


    public static void printVegan(List<Pizza> menu) {
        Stream<Pizza> pizzaStream = menu.stream();
        pizzaStream.filter(Pizza::isVegan)
                .forEach(pizza -> System.out.println(pizza.name()));
    }

    public static void printCalories(List<Pizza> menu) {
        Stream<Pizza> pizzaStream = menu.stream();
        pizzaStream.forEach(pizza -> System.out.println("Total calories in " + pizza.name() + ": " + pizza.calculateTotalCalories() + ""));
    }


    public static void printContainsCelery(List<Pizza> menu) {
        List<Pizza> pizzasWithCelery = menu.stream()
                .filter(pizza -> pizza.ingredientsWithWeight().containsKey(Ingredients.CELERY)).toList();

        System.out.println("Pizzas with celery:");
        pizzasWithCelery.forEach(pizza -> System.out.println(pizza.name()));
    }

    public static boolean checkIsVeganAndContainsTomatoAndPeppers(List<Pizza> menu) {
        return menu.stream()
                .anyMatch(pizza ->
                        pizza.isVegan() &&
                                pizza.ingredientsWithWeight().containsKey(Ingredients.TOMATO) &&
                                pizza.ingredientsWithWeight().containsKey(Ingredients.PEPPERS)
                );
    }

    public static boolean doesContainMozzarella(List<Pizza> menu) {
        return menu.stream()
                .allMatch(pizza -> pizza.ingredientsWithWeight().containsKey(Ingredients.MOZZARELLA));
    }

    public static Pizza getPizzaWithHighestCalories(List<Pizza> menu) {
        return menu.stream()
                .max(Comparator.comparingInt(Pizza::calculateTotalCalories))
                .orElseThrow(() -> new IllegalStateException("No pizzas in the menu"));
    }

    public static Pizza getPizzaWithLowestCalories(List<Pizza> menu) {
        return menu.stream()
                .min(Comparator.comparingInt(Pizza::calculateTotalCalories))
                .orElseThrow(() -> new IllegalStateException("No pizzas in the menu"));
    }

}