package drinkkiarkisto.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add(
                "CREATE TABLE Drink (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name varchar(255)\n"
                + ");"
        );
        lista.add(
                "CREATE TABLE Ingredient (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name varchar(255)\n"
                + ");"
        );
        lista.add(
                "CREATE TABLE DrinkIngredient (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    drink_id Integer,\n"
                + "    ingredient_id Integer,\n"
                + "    orderNo Integer,\n"
                + "    amount varchar(255),\n"
                + "    directions varchar(255),\n"
                + "    FOREIGN KEY (drink_id) REFERENCES Drink(id),\n"
                + "    FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id)\n"
                + ");"
        );
        return lista;
    }
}
