# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksessa on toteutettuna tuttu Pong-peli, jossa mailojen avulla pyritään pitämään liikkuva pallo pelialueella. Pallon osuminen mailan takana olevaan seinään kasvattaa vastapelaajan pistemäärää. Peli päättyy jommankumman pelaajan voittoon eli pelaajan saavuttaessa ennalta määrätyn pistemäärän. Toteutetussa sovelluksessa voittoon tarvittava pistemäärä on 10.

## Käyttäjät

Sovellus ei vaadi erikseen kirjautumista, mutta peliin voi luoda oman käyttäjänimen ja kerryttää samalle käyttäjänimelle saatuja pisteitä.

## Käyttöliittymäsuunnitelma

Käyttöliittymä koostuu kolmesta eri näkymästä: aloitussivusta, itse pelistä ja pelin päättymistä seuraavasta sivusta. 

* Aloitussivulla määritellään pelaajien käyttäjänimet ja pelin vaikeusaste. 
* Pelisivulla tapahtuu pelin toteutus ja pelaajien pisteiden kerrytys, jonka jälkeen siirrytään pelin päätyttyä automaattisesti loppusivulle. 
* Loppusivulla kerrotaan pelikierroksen voittaja ja esitetään Top 5 -lista kaikkien aikojen parhaiten suorituneista pelaajista pistemäärineen. Loppusivulta voi siirtyä uudelle pelikierrokselle, palata aloitussivulle vaihtamaan pelin asetukset tai sammuttaa pelin.

## Perusversion tarjoama toiminnallisuus

### Ennen peliä

Pelin aloitussivulla kysytään pelin vaikeusasteen lisäksi pelaajien käyttäjänimiä. Jos pelaaja on pelannut Pongia jo aiemmin, pelaajan käyttäjänimi on jo tietokannan muistissa ja saman käyttäjänimen pistesaalista voi peli peliltä kasvattaa. Uuden käyttäjänimen luominen tapahtuu syöttämällä käyttäjänimeä kysyvään kenttään aiemmin esiintymättömän käyttäjänimen.

### Pelin aikana

Pelin aikana pelaajat pyrkivät osumaan mailallaan liikkuvaan palloon, jolloin pallo kimpoaa vastapelaajan pelialueelle. Mikäli pelaajan maila ei ehdi kimmottaa palloa ennen sen osumista mailan takana olevaan seinään, saa vastapelaaja tästä pisteen. Pelin aikana saaduista pisteistä pidetään kirjaa pelisivun ylälaidassa. Kun jompikumpi pelaajista saa ennalta määrätyn pistemäärän, peli päättyy.

### Pelin jälkeen

Pelin päätyttyä siirrytään automaattisesti loppusivulle, josta käy ilmi pelaajien menestys juuri pelatulla kierroksella. Loppusivu ilmoittaa voittajan nimen ja kaikkien aikojen parhaiten suoriutuneiden käyttäjänimien Top 5 -listauksen. Loppusivulta on mahdollista siirtyä "Re-start" -nappia painamalla uudelle pelikierrokselle. Tällöin käyttäjänimet pysyvät samoina. "New game" -nappia painamalla siirrytään aloitussivulle, jolloin käyttäjänimiä ja pelin vaikeustasoa on mahdollista vaihtaa. "End game" -nappi puolestaan sammuttaa pelisovelluksen.

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennettiin seuraavilla toiminnallisuuksilla:

* Kolme erilaista pelin vaikeusastetta
* Pallon nopeus kasvaa pelikierroksen edetessä
* Realistinen pallon kimpoamissuunta mailan osumiskohdan perusteella
* Pallon alkunopeuden voi päättää pelin aloitussivulla vaikeusasteen mukaan (helppo, keski, vaikea), ChoiceBox
* Tietokannasta löytyvien käyttäjänimien valitseminen aloitussivulla, ComboBox
* Virheellisen tiedon syöttäminen pelin asetuksissa, virheviestit
* Visuaalinen hienosäätö

Peliä voisi edelleen viedä eteenpäin seuraavilla lisäyksillä:

* Pallon alkunopeus ja nopeuden muutos vaikuttaa mailojen liikkeen nopeuteen
* Mahdolliset bugit pallon kimpoamisessa mailasta
* Käyttäjän valittavissa oleva, voittoon tarvittava pistemäärä
* Aloitussivun virheviestit eivät siirrä aloitussivun laatikoita ja nappeja
* Kuvan integroiminen osaksi sovellusta (ei erillisenä tiedostona)
* Rajattu määrä luotavia käyttäjänimiä
* Pelin pelaaminen yksin
