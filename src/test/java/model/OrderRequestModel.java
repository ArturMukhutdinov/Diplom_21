package model;

import java.util.List;

public class OrderRequestModel {
    private List<String> ingredients;

    public OrderRequestModel(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}