/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drinkkiarkisto.dao;

import drinkkiarkisto.database.Database;
import drinkkiarkisto.domain.Drink;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author John-Robert
 */
public class DrinkDao extends AbstractNamedObjectDao<Drink> {

    public DrinkDao(Database database, String tableName) {
        super(database, tableName);
    }

    @Override
    public Drink createFromRow(ResultSet resultSet) throws SQLException {
        return new Drink(
                resultSet.getInt("id"),
                resultSet.getString("name"));
    }

}
