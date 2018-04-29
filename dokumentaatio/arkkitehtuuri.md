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

joista jokainen on toteutettu omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-oliona. Vain yksi näkymä voi olla kerrallaan näkyvänä eli sijoitettuna sovelluksen [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Käyttöliittymä on rakennettu ohjelmallisesti luokassa [pong.ui.PongApplication](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/ui/PongApplication.java).

Näkymistä _aloitussivu_ käsittää pelaajien käyttäjänimien luomisen tai valitsemisen olemassa olevista käyttäjänimistä sekä pelin vaikeustason valinnan. Aloitusnäkymästä päästään _pelinäkymään_, joka pitää sisällään itse pelin logiikkoineen. Pelikierroksen päättyessä jommankumman pelaajan voittoon siirrytään _lopetussivulle_, joka ilmoittaa kierroksen voittaneen pelaajan käyttäjänimen, esittää pistemäärän perusteella viiden parhaan käyttäjänimen listauksen ja mahdollistaa kolmella napilla joko uuden pelikierroksen, uuden pelin tai pelin lopettamisen.

Käyttöliittymä on pyritty eristämään pelilogiikan osalta täysin sovelluslogiikasta - se kutsuu sopivin parametrein pelilogiikan muutoin toteuttavan olion _logics_ metodeja.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat [Ball](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/domain/Ball.java), [Movement](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/domain/Movement.java), [Paddle](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/domain/Paddle.java), [Player](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/domain/Player.java) ja [Score](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/domain/Score.java). Luokat edustavat pelilogiikkaan kuuluvaa palloa, pelimailoja, sekä pallon että mailojen liikettä, pelikierroksen pistetilannetta ja sovelluksen käytössä olevasta tietokannasta haettavia tai sinne talletettavia pelaajatietoja.

Toiminnallisista kokonaisuuksista ja pelilogiikan kokoamisesta vastaa luokan [PongLogics](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/logics/PongLogics.java) ainoa olio. Luokka tarjoaa käyttöliittymän pelilogiikkaa koskeville toiminnoille omat metodinsa. Näitä ovat esimerkiksi:

- void moveBall()
- void movePaddles()
- void ballHitsPaddle()
- void paddlesOnBoard(int gameHeight)

Ohessa on sovelluksen osien relaatioita kuvaava luokkakaavio.

![Luokkakaavion kuva](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_luokkakaavio.png)

## Tietojen pysyväistallennus

Pakkauksen _pong.dao_ luokka PlayerDao huolehtii käyttäjänimitietojen ja niihin liittyvien pistemäärien tallettamisesta sovelluksen käytössä olevaan tietokantaan. Luokka noudattaa [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallia. Koska sovelluksessa on käytössä ainoastaan yksi tietokantataulu ja tätä koskeva yksi DAO-luokka, ei sovellukseen ole luotu erillistä eristettyä rajapintaa DAO-toteutuksen luomiseksi.

Tietokanta on toteutettu SQLite-tyyppiseen tiedostoon [player.db], jossa on ainoana tietokantatauluna taulu Player. Taulu on luotu komennolla CREATE TABLE Player (id integer PRIMARY KEY, name varchar(8), score integer). Sovellus tallettaa pelaajatietoihin siis sovelluksen automaattisesti luoman tunnisteen eli _id_:n, käyttäjänimen ja tähän käyttäjänimeen liittyvän pistesaldon.

## Päätoiminnallisuudet

### Vasemman mailan liikuttaminen alas
![Sekvenssikaavio](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_%20Move%20left%20paddle.png)

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä

Sovelluksen graafinen käyttöliittymä on toteutettu määrittelemällä lähes koko käyttöliittymän rakenne luokan _PongApplication_ metodissa _start_. Sovelluksen eri näkymät rakentava koodi kannattaisi erottaa kutakin näkymää vastaaviksi metodeiksi tai luokiksi.
