/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drinkkiarkisto.dao;

import drinkkiarkisto.database.Database;
import drinkkiarkisto.domain.Ingredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John-Robert
 */
public class IngredientDao extends AbstractNamedObjectDao<Ingredient> {

    public IngredientDao(Database database, String tableName) {
        super(database, tableName);
    }

    @Override
    public Ingredient createFromRow(ResultSet resultSet) throws SQLException {
        return new Ingredient(
                resultSet.getInt("id"),
                resultSet.getString("name"));
    }

    public List<Ingredient> findAllByDrinkId(Integer drinkId) throws SQLException {
        String query = "SELECT Ingredient.id AS id, Ingredient.name AS name FROM Ingredient\n"
                + "    JOIN DrinkIngredient ON Ingredient.id = DrinkIngredient.ingredient_id\n"
                + "    WHERE DrinkIngredient.drink_id = ?\n"
                + "    ORDER BY DrinkIngredient.orderNo ASC";
        List<Ingredient> ingredients = new ArrayList<>();
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, drinkId);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                ingredients.add(createFromRow(result));
            }
        }
        return ingredients;
    }
}
