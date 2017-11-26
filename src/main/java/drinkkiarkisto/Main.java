package drinkkiarkisto;

import drinkkiarkisto.dao.DrinkDao;
import drinkkiarkisto.dao.DrinkIngredientDao;
import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import drinkkiarkisto.database.Database;
import drinkkiarkisto.dao.IngredientDao;
import drinkkiarkisto.domain.Drink;
import drinkkiarkisto.domain.DrinkIngredient;
import drinkkiarkisto.domain.Ingredient;
import drinkkiarkisto.domain.IngredientWithInstructions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:drinkarchive.db");
        database.init();

        DrinkDao drinkDao = new DrinkDao(database, "Drink");
        IngredientDao ingredientDao = new IngredientDao(database, "Ingredient");
        DrinkIngredientDao drinkIngredientDao = new DrinkIngredientDao(database, "DrinkIngredient");

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/drinks", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("drinks", drinkDao.findAll());

            return new ModelAndView(map, "drinks");
        }, new ThymeleafTemplateEngine());

        post("/drinks", (req, res) -> {
            Drink drink = new Drink(-1, req.queryParams("name"));
            Drink drink2 = drinkDao.saveOrUpdate(drink);
            System.out.println(drink2.getId());

            res.redirect("/drinks/" + drink2.getId());
            return "";
        });

        get("/ingredients", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("ingredients", ingredientDao.findAll());

            return new ModelAndView(map, "ingredients");
        }, new ThymeleafTemplateEngine());

        post("/ingredients", (req, res) -> {
            Ingredient ingredient = new Ingredient(-1, req.queryParams("name"));
            Ingredient ingredient2 = ingredientDao.saveOrUpdate(ingredient);
            System.out.println(ingredient2.getId());

            res.redirect("/ingredients");
            return "";
        });

        get("/drinks/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            Integer drinkkiId = Integer.parseInt(req.params(":id"));
            List<Ingredient> ingredients = ingredientDao.findAll();
            List<DrinkIngredient> dIngredients = drinkIngredientDao.findAllByDrinkId(drinkkiId);
            List<IngredientWithInstructions> ingredientsWi = new ArrayList<>();;
            for (DrinkIngredient object : dIngredients) {
                String name = ingredients.stream().filter(
                        p -> Objects.equals(object.getIngredientId(), p.getId())).findFirst().get().getName();            
                ingredientsWi.add(new IngredientWithInstructions(
                object.getOrderNo(),object.getAmount(),object.getDirections(),object.getId(),name
                ));
            }

            map.put("drink", drinkDao.findOne(drinkkiId));
            map.put("allIngredients", ingredients);
            map.put("ingredients", ingredientsWi);

            return new ModelAndView(map, "drink");
        }, new ThymeleafTemplateEngine());

        post("/drinks/:id", (req, res) -> {
            Integer drinkId = Integer.parseInt(req.params(":id"));
            DrinkIngredient drinkIngredient = new DrinkIngredient(
                    -1,
                    drinkId,
                    Integer.parseInt(req.queryParams("ingredientId")),
                    Integer.parseInt(req.queryParams("orderNo")),
                    req.queryParams("amount"),
                    req.queryParams("directions"));
            drinkIngredientDao.save(drinkIngredient);

            res.redirect("/drinks/" + drinkId);
            return "";
        });
        
        delete("/drinks/:did/:iid", (req, res) -> {
            Integer DrinkIngredientId = Integer.parseInt(req.params(":iid"));
            drinkIngredientDao.delete(DrinkIngredientId);
            res.redirect("/drinks/" + Integer.parseInt(req.params(":did")));
            return "";         
        });
    }
}
