public enum Ingredients {
    MOZZARELLA(250),
    MUSHROOMS(20),
    SALAMI(300),
    ONION(40),
    TOMATO(18),
    PEPPERS(30),
    CELERY(16);

    private final int caloriesPer100g;

    Ingredients(int caloriesPer100g) {
        this.caloriesPer100g = caloriesPer100g;
    }

    public int getCaloriesPer100g() {
        return caloriesPer100g;
    }
}