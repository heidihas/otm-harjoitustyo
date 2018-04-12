# Ohjelmistotekniikan menetelmät - harjoitustyö

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)


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

### Checkstyle

Tiedoston [checkstyle.xml](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedoston _target/site/checkstyle.html_
