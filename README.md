# Drinkkiarkisto

Harjoitustehtävä

Vastaukset kysymyksiin:

<h2>Voiko sovelluksessa mennä katsomaan listausta ainesosista (tai vastaavasta)?<h2/>
    <p>Kyllä, ” /ingredients” GET<p/>
Voiko sovelluksessa mennä katsomaan listausta annoksista (tai vastaavasta)?
    Kyllä, ” /drinks” GET
Voiko ainesosia lisätä?
    Kyllä, ” /ingredients” POST
Voiko ainesosia poistaa?
    Kyllä, "/ingredients/delete/:id" POST
Voiko annoksia lisätä?
    Kyllä, ” /drinks” POST
Voiko annoksia poistaa?
    Kyllä, "/drinks/delete/:id" POST
Voiko annokseen lisätä useita ainesosia? Voiko saman ainesosan lisätä useampaan annokseen?
    Kyllä, Kyllä, ” /drinks/:id” POST
Voiko annoksen reseptiä (=ainesosia) katsoa?
    Kyllä, ” /drinks/:id” GET
Näkyvätkö reseptin vaiheet oikeassa järjestyksessä?
    Kyllä, ” /drinks/:id” GET (DrinkIngredientDao -> findAllByDrinkId())
Onko sovelluksella etusivu, joka on nähtävissä sovelluksen juuripolussa (“/”)?
    Kyllä, ”index.html”


