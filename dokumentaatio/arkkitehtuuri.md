# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa viisitasoista kerrosarkkitehtuuria. Alla oleva kuva osoittaa koodin pakkausrakenteen.

![Pakkauskaavion kuva](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pakkauskaavio.png)

Pakkaus _pong.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _pong.logics_ pelin säännöt ja logiikan, _pong.domain_ muun sovelluslogiikan pelielementteineen, _pong.dao_ tietojen pysyväistallennuksesta vastaavan koodin ja _pong.database_ yhteyden sovellukseen kuuluvaan tietokantaan.

## Käyttöliittymä

Käyttöliittymä käsittää kolme erillistä näkymää

- aloitussivu
- pelinäkymä
- lopetussivu

joista jokainen on toteutettu omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-oliona. Vain yksi näkymä voi olla kerrallaan näkyvänä eli sijoitettuna sovelluksen [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Käyttöliittymä on rakennettu ohjelmallisesti luokassa [pong.ui.PongApplication].

Näkymistä _aloitussivu_ käsittää pelaajien käyttäjänimien luomisen tai valitsemisen olemassa olevista käyttäjänimistä sekä pelin vaikeustason valinnan. Aloitusnäkymästä päästään _pelinäkymään_, joka pitää sisällään itse pelin logiikkoineen. Pelikierroksen päättyessä jommankumman pelaajan voittoon siirrytään _lopetussivulle_, joka ilmoittaa kierroksen voittaneen pelaajan käyttäjänimen, esittää pistemäärän perusteella viiden parhaan käyttäjänimen listauksen ja mahdollistaa kolmella napilla joko uuden pelikierroksen, uuden pelin tai pelin lopettamisen.

Käyttöliittymä on pyritty eristämään pelilogiikan osalta täysin sovelluslogiikasta - se kutsuu sopivin parametrein pelilogiikan muutoin toteuttavan olion _logics_ metodeja.

## Sovelluslogiikka

## Luokkakaavio
![Luokkakaavion kuva](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_luokkakaavio.png)

## Päätoiminnallisuudet

### Vasemman mailan liikuttaminen alas
![Sekvenssikaavio](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_%20Move%20left%20paddle.png)

## Tietojen pysyväistallennus

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä

Sovelluksen graafinen käyttöliittymä on toteutettu määrittelemällä lähes koko käyttöliittymän rakenne luokan _PongApplication_ metodissa _start_. Sovelluksen eri näkymät rakentava koodi kannattaisi erottaa kutakin näkymää vastaaviksi metodeiksi tai luokiksi.
