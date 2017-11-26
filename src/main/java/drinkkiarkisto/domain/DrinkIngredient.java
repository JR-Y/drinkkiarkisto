/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drinkkiarkisto.domain;

/**
 *
 * @author John-Robert
 */
public class DrinkIngredient {
    private Integer id;
    private Integer drinkId;
    private Integer ingredientId;
    private Integer orderNo;
    private String amount;
    private String directions;

    public DrinkIngredient(Integer id, Integer drinkId, Integer ingredientId, Integer orderNo, String amount, String directions) {
        this.id = id;
        this.drinkId = drinkId;
        this.ingredientId = ingredientId;
        this.orderNo = orderNo;
        this.amount = amount;
        this.directions = directions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
    
}
