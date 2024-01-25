import java.util.Map;

public class Pizza {
    private final Map<Ingredients, Integer> ingredientsWithWeight;
    private final boolean isVegan;
    private final String name;

    public Pizza(Map<Ingredients, Integer> ingredientsWithWeight, boolean isVegan, String name) {
        this.ingredientsWithWeight = ingredientsWithWeight;
        this.isVegan = isVegan;
        this.name = name;
    }

    public Map<Ingredients, Integer> getIngredientsWithWeight() {
        return ingredientsWithWeight;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public String getName() {
        return name;
    }

    public int calculateTotalCalories() {
        return ingredientsWithWeight.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getCaloriesPer100g() * entry.getValue() / 100)
                .sum();
    }

    public float calculateCaloriesPerSlice(int slices) {
        int totalCalories = calculateTotalCalories();
        int totalSlices = getTotalSlices();

        if (totalSlices > 0) {
            return (float) totalCalories / totalSlices * slices;
        } else {
            return 0;
        }
    }

    int getTotalSlices() {
        return 8;
    }

    @Override
    public String toString() {
        return name;
    }
}
