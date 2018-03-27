# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksessa on toteutettuna tuttu Pong-peli, jossa mailojen avulla pyritään pitämään liikkuva pallo pelialueella. Pallon osuminen mailan takana olevaan seinään kasvattaa vastapelaajan pistemäärää. Peli päättyy jommankumman pelaajan voittoon eli pelaajan saavuttaessa ennalta määrätyn pistemäärän.

## Käyttäjät

Sovellus ei vaadi erikseen kirjautumista, mutta peliin voi luoda oman käyttäjänimen ja kerryttää samalle käyttäjänimelle saatuja pisteitä.

## Käyttöliittymäsuunnitelma

Käyttöliittymä koostuu kolmesta eri näkymästä: aloitussivusta, itse pelistä ja pelin päättymistä seuraavasta sivusta. 

* Aloitussivulla määritellään pelaajien käyttäjänimet. 
* Pelisivulla tapahtuu pelin toteutus ja pelaajien pisteiden kerrytys, jonka jälkeen siirrytään pelin päätyttyä automaattisesti loppusivulle. 
* Loppusivulla kerrotaan pelikierroksen voittaja, molempien pelaajien pistemäärät edeltäneen pelikierroksen lopussa ja esitetään top5-lista kaikkien aikojen parhaiten suorituneista pelaajista pistemäärineen. Loppusivulta voi siirtyä uudelle pelikierrokselle tai sammuttaa pelin.

## Perusversion tarjoama toiminnallisuus

### Ennen peliä

Pelin aloitussivulla kysytään pelaajien käyttäjänimiä. Jos pelaaja on pelannut Pongia jo aiemmin, pelaajan käyttäjänimi on jo tietokannan muistissa ja saman käyttäjänimen pistesaalista voi peli peliltä kasvattaa. Uuden käyttäjänimen luominen tapahtuu syöttämällä käyttäjänimeä kysyvään kenttään aiemmin esiintymättömän käyttäjänimen.

### Pelin aikana

Pelin aikana pelaajat pyrkivät osumaan mailallaan liikkuvaan palloon, jolloin pallo kimpoaa vastapelaajan pelialueelle. Mikäli pelaajan maila ei ehdi kimmottaa palloa ennen sen osumista mailan takana olevaan seinään, saa vastapelaaja tästä pisteen. Pelin aikana saaduista pisteistä pidetään kirjaa pelisivun ylälaidassa. Kun jompikumpi pelaajista saa ennalta määrätyn pistemäärän, peli päättyy.

### Pelin jälkeen



## Jatkokehitysideoita
