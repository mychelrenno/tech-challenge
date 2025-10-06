package com.fiap.tech_challenge.core.domain.restaurant;

public enum RestaurantType {
    FAST_FOOD("Fast Food"),
    FINE_DINING("Fine Dining"),
    CASUAL_DINING("Casual Dining"),
    BUFFET("Buffet"),
    CAFE("Cafe"),
    PIZZERIA("Pizzeria"),
    FOOD_TRUCK("Food Truck"),
    DELIVERY_ONLY("Delivery Only"),
    STEAKHOUSE("Steakhouse"),
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    JAPANESE("Japanese"),
    CHINESE("Chinese"),
    ITALIAN("Italian"),
    BRAZILIAN("Brazilian");

    private final String description;

    RestaurantType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
