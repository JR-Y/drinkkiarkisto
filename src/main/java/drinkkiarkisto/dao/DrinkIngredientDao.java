/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drinkkiarkisto.dao;

import drinkkiarkisto.database.Database;
import drinkkiarkisto.domain.DrinkIngredient;
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
public class DrinkIngredientDao implements Dao<DrinkIngredient, Integer> {

    protected Database database;
    protected String tableName;
    DrinkDao drinkDao = new DrinkDao(database, "Drink");
    IngredientDao ingredientDao = new IngredientDao(database, "Ingredient");

    public DrinkIngredientDao(Database database, String tableName) {
        this.database = database;
        this.tableName = tableName;
    }

    @Override
    public DrinkIngredient findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DrinkIngredient> findAll() throws SQLException {
        List<DrinkIngredient> DRAList = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT * FROM " + tableName).executeQuery()) {

            while (result.next()) {
                DRAList.add(createFromRow(result));
            }
        }

        return DRAList;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        String query = " DELETE FROM " + tableName + " WHERE id = ? ";
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, key);
            ResultSet result = stmt.executeQuery();
        }        
    }

    private DrinkIngredient createFromRow(ResultSet result) throws SQLException {
        return new DrinkIngredient(
                result.getInt("id"),
                result.getInt("drink_id"),
                result.getInt("ingredient_id"),
                result.getInt("orderNo"),
                result.getString("amount"),
                result.getString("directions")
        );
    }

    public List<DrinkIngredient> findAllByDrinkId(Integer drinkId) throws SQLException {
        
        String query = "SELECT * FROM " + tableName + "\n"
                + "    WHERE DrinkIngredient.drink_id = ?\n"
                + "    ORDER BY DrinkIngredient.orderNo ASC";
        List<DrinkIngredient> drinkIngredients = new ArrayList<>();
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, drinkId);
            ResultSet result = stmt.executeQuery();
            System.out.println(result.toString());

            while (result.next()) {
                drinkIngredients.add(createFromRow(result));
            }
        }
        return drinkIngredients;
    }

    /*
    @Override
    public Message findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Message findOneByMessageAndTime(Message object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE message = ? AND msgTime = ?");
            stmt.setString(1, object.getMessage());
            stmt.setDate(2, object.getMsgTime());

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return createFromRow(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error when looking for a row in " + tableName);
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Message> findAll() throws SQLException {
        List<Message> messages = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT * FROM " + tableName).executeQuery()) {

            while (result.next()) {
                messages.add(createFromRow(result));
            }
        }

        return messages;
    }

    public List<Message> findAllByTopic(Integer topic_id) throws SQLException {
        String query = "SELECT * FROM "+ tableName +
"    WHERE topic_id = ? " +
"    ORDER BY Message.msgTime DESC";
        List<Message> messages = new ArrayList<>();

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, topic_id);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                messages.add(createFromRow(result));
            }
        }
        return messages;
    }

    @Override
    public Message saveOrUpdate(Message object) throws SQLException {

        if (object.getId() == -1) {
            return save(object);
        } else {
            return update(object);
        }

    }
     */
    public void save(DrinkIngredient object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + tableName + " (drink_id,ingredient_id,orderNo,amount,directions) "
                    + "VALUES (?,?,?,?,?)");
            stmt.setInt(1, object.getDrinkId());
            stmt.setInt(2, object.getIngredientId());
            stmt.setInt(3, object.getOrderNo());
            stmt.setString(4, object.getAmount());
            stmt.setString(5, object.getDirections());
            int changes = stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Fail" + e);
        }
    }
}
