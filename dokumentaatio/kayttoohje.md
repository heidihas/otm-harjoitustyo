# Käyttöohje

Lataa tiedosto [pong-1.0.jar](https://github.com/heidihas/otm-harjoitustyo/releases/tag/viikko5).

Ohjelma olettaa, että sen käynnistyshakemistossa ovat tiedostot _pong.png_ ja _player.db_, joista ensimmäinen käsittää sovelluksen aloitussivulla olevan kuvan ja jälkimmäinen sovelluksen käyttämän tietokannan.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar pong-1.0.jar
```

## Pelaajien käyttäjänimien valinta

Sovellus käynnistyy alla olevaan aloitussivunäkymään.

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pic_1.png" width="600">

Aloitussivulla käyttäjän on tarkoitus valita molempien pelaajien käyttäjänimet sekä seuraavan pelikierroksen vaikeustaso. Mikäli pelaaja on käyttänyt sovellusta jo ennen, on hänen aiempi käyttäjänimensä tallettunut sovelluksen käyttämän tietokannan muistiin. Samalle käyttäjänimelle on mahdollista kerryttää pistesaldoa. 

Aloitussivulla olemassa olevan käyttäjänimen voi valita avaamalla käyttäjänimivalikon tekstikentän oikeassa reunassa olevaa nuolta klikkaamalla. Näkymä on alla olevan kaltainen.

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pic_2.png" width="600">

Tekstikenttään on myös mahdollista kirjoittaa joko olemassa oleva tai uusi luotava käyttäjänimi.

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pic_3.png" width="600">

Käyttäjän klikatessa painiketta _Start_ sovellus tarkistaa annettujen tietojen oikeellisuuden. Mikäli valittu käyttäjänimi on pidempi kuin 8 merkkiä, tekstikenttä on jätetty tyhjäksi tai nimi koostuu välilyönneistä, ilmoittaa sovellus tästä punaisella virhetekstillä _Maximum 8 characters_ tekstikentän vieressä. Jos taas molemmille pelaajille on valittu sama käyttäjänimi, ilmestyy molempien tekstikenttien viereen virheteksti _Choose different names_. Virhetekstien yhteydessä sovellus myös nollaa aiemmin tekstikenttiin syötetyn merkkijonon. 

Lisäksi sovellus tarkistaa, onko haluttu pelin vaikeustaso valittu ennen _Start_-painikkeen klikkaamista. Mikäli ei, ilmoittaa sovellus tästä virhetekstillä _Choose level_.

Alla näkymä kahden virhetekstin tapauksessa.

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pic_4.png" width="600">

Kun virheet on korjattu, kuten alla olevassa kuvassa, voidaan painaa uudelleen _Start_-painiketta ja edetä varsinaiseen peliin.

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pic_5.png" width="600">

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pic_6.png" width="600">

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pic_7.png" width="600">

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pic_8.png" width="600">
