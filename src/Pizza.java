import java.util.Map;

public record Pizza(Map<Ingredients, Integer> ingredientsWithWeight, boolean isVegan, String name) {

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
