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
public class IngredientWithInstructions extends Ingredient{
    
    private Integer orderNo;
    private String amount;
    private String directions;
    
    public IngredientWithInstructions(Integer orderNo, String amount, String directions, Integer id, String name) {
        super(id, name);
        this.orderNo = orderNo;
        this.amount = amount;
        this.directions = directions;
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
