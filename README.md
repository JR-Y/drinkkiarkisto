# Drinkkiarkisto

Harjoitustehtävä

<h1>Vastaukset kysymyksiin:</h1>

<h2>Voiko sovelluksessa mennä katsomaan listausta ainesosista (tai vastaavasta)?</h2>
    <p>Kyllä, ” /ingredients” GET</p>
<h2>Voiko sovelluksessa mennä katsomaan listausta annoksista (tai vastaavasta)?</h2>
    <p>Kyllä, ” /drinks” GET</p>
<h2>Voiko ainesosia lisätä?</h2>
    <p>Kyllä, ” /ingredients” POST</p>
<h2>Voiko ainesosia poistaa?</h2>
    <p>Kyllä, "/ingredients/delete/:id" POST</p>
<h2>Voiko annoksia lisätä?</h2>
    <p>Kyllä, ” /drinks” POST</p>
<h2>Voiko annoksia poistaa?</h2>
    <p>Kyllä, "/drinks/delete/:id" POST</p>
<h2>Voiko annokseen lisätä useita ainesosia? Voiko saman ainesosan lisätä useampaan annokseen?</h2>
    <p>Kyllä, Kyllä, ” /drinks/:id” POST</p>
<h2>Voiko annoksen reseptiä (=ainesosia) katsoa?</h2>
    <p>Kyllä, ” /drinks/:id” GET</p>
<h2>Näkyvätkö reseptin vaiheet oikeassa järjestyksessä?</h2>
    <p>Kyllä, ” /drinks/:id” GET (DrinkIngredientDao -> findAllByDrinkId())</p>
<h2>Onko sovelluksella etusivu, joka on nähtävissä sovelluksen juuripolussa (“/”)?</h2>
    <p>Kyllä, ”index.html”</p>


