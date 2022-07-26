package util;

import model.OrderRequestModel;

import java.util.List;

public class OrderDataProducer {
    private static final String INGREDIENT_1 = "61c0c5a71d1f82001bdaaa6d";
    private static final String INGREDIENT_2 = "61c0c5a71d1f82001bdaaa6f";
    private static final String INCORRECT_INGREDIENT_3 = "wrong hash";

    public static OrderRequestModel getCorrectOrder() {
        return new OrderRequestModel(List.of(INGREDIENT_1, INGREDIENT_2));
    }

    public static OrderRequestModel getEmptyOrder() {
        return new OrderRequestModel(List.of());
    }

    public static OrderRequestModel getIncorrectOrder() {
        return new OrderRequestModel(List.of(INGREDIENT_1, INCORRECT_INGREDIENT_3));
    }
}