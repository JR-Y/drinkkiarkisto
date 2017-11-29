/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drinkkiarkisto.dao;

/**
 *
 * @author John-Robert
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import drinkkiarkisto.database.Database;
import drinkkiarkisto.database.Database;
import drinkkiarkisto.domain.AbstractNamedObject;

public abstract class AbstractNamedObjectDao<T extends AbstractNamedObject>
        implements Dao<T, Integer> {

    protected Database database;
    protected String tableName;

    public AbstractNamedObjectDao(Database database, String tableName) {
        this.database = database;
        this.tableName = tableName;
    }

    @Override
    public T findOne(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM " + tableName + " WHERE id = ?");
            stmt.setInt(1, key);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return createFromRow(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error when looking for a row in " + tableName + " with id " + key);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> tasks = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, name FROM " + tableName).executeQuery()) {

            while (result.next()) {
                tasks.add(createFromRow(result));
            }
        }

        return tasks;
    }

    public T saveOrUpdate(T object) throws SQLException {
        // simply support saving -- disallow saving if task with 
        // same name exists
        T byName = findByName(object.getName());

        if (byName != null) {
            return byName;
        }

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + tableName + " (name) VALUES (?)");
            stmt.setString(1, object.getName());
            stmt.executeUpdate();
        }

        return findByName(object.getName());

    }

    private T findByName(String name) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM " + tableName + " WHERE name = ?");
            stmt.setString(1, name);

            try (ResultSet result = stmt.executeQuery()) {
                if (!result.next()) {
                    return null;
                }

                return createFromRow(result);
            }
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        String query = " DELETE FROM " + tableName + " WHERE id = ? ";
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, key);
            stmt.executeUpdate();
        }
    }

    public abstract T createFromRow(ResultSet resultSet) throws SQLException;

}
