# Ohjelmistotekniikan menetelmät - harjoitustyö

## Pong

Sovelluksessa on toteutettuna tuttu Pong-peli, jossa kaksi pelaajaa kilpailevat pelikierroksen voitosta pyrkimällä pitämään liikkuvan pallon pelialueella. Pallon osuminen vastapelaajan mailan takana olevaan seinään kasvattaa pelaajan pistemäärää. Pelikierros päättyy jommankumman pelaajan voittoon eli jommankumman pelaajan saavuttaessa ennalta määrätyn pistemäärän. Sovellusta käytetään käyttäjänimillä, jotka talletetaan tietokantaan. Käyttäjänimille on mahdollista kerryttää pisteitä jokaisen pelatun pelikierroksen aikana, myös silloin kun pelaaja häviää. Pelikierroksen loputtua pelaajille kerrotaan viiden parhaiten menestyneen käyttäjänimen järjestys pistemäärineen.

Sovellus on toteutettu osana kevään 2018 Ohjelmistotekniikan menetelmät -kurssia.

## Dokumentaatio

[Käyttöohje](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/heidihas/otm-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/heidihas/otm-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/heidihas/otm-harjoitustyo/releases/tag/viikko7)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedoston _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Pong-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedoston _target/site/apidocs/index.html_

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedoston _target/site/checkstyle.html_
