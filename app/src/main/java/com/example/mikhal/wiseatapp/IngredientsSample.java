package com.example.mikhal.wiseatapp;

class IngredientsSample {
    private String name;
    private String foodGroup;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    @Override
    public String toString() {
        return "IngredientsSample{" +
                "name='" + name + '\'' +
                ", foodGroup='" + foodGroup + '\'' +
                '}';
    }
}
