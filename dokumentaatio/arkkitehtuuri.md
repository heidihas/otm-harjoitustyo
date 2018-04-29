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

Tietokanta on toteutettu SQLite-tyyppiseen tiedostoon _player.db_, jossa on ainoana tietokantatauluna taulu Player. Taulu on luotu komennolla CREATE TABLE Player (id integer PRIMARY KEY, name varchar(8), score integer). Sovellus tallettaa pelaajatietoihin siis sovelluksen automaattisesti luoman tunnisteen eli _id_:n, käyttäjänimen ja tähän käyttäjänimeen liittyvän pistesaldon.

## Päätoiminnallisuudet

Ohessa kuvataan sovelluksen toimintalogiikkaa muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

### Uuden pelin aloittaminen

Kun aloitussivun pelaajanimien syötekenttiin on joko kirjoitettu tai valittu käyttäjänimet, pelin toivottu vaikeustaso on valittu ja klikataan painiketta _startButton_, etenee sovelluksen kontrolli seuraavasti:

![Uuden pelin aloittaminen](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_%20Start%20a%20new%20game_longer.png)

### Vasemman mailan liikuttaminen alas

Kun käyttäjä painaa näppäintä s eli liikuttaa vasemmanpuoleista mailaa alas, etenee sovelluksen kontrolli seuraavasti:

![Vasemman mailan liikuttaminen alas](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_%20Move%20left%20paddle.png)

### Pallon osuminen oikeaan seinään

Kun oikeanpuoleinen pelaaja ei onnistu kimmottamaan palloa omalta pelialueeltaan ja käyttöliittymän [handle-metoti](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/ui/PongApplication.java#L321) havaitsee sen, etenee sovelluksen kontrolli seuraavasti:

![Pallon osuminen oikeaan seinään](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_%20Ball%20hits%20right%20wall.png)

### Muut toiminnallisuudet

Sama periaate toistuu sovelluksen muissa toiminnallisuuksissa: käyttöliittymän tapahtumakäsittelijä ja [handle-metodi](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/ui/PongApplication.java#L321) kutsuu sovelluslogiikan metodeja, ja sovelluslogiikka päivittää tarpeen mukaan sovelluksen/pelin tilaa. Kontrollin palatessa käyttöliittymään päivittynyt tieto korjaantuu visuaalisesti pelin piirtoalustalla käyttöliittymän piirtäessä saamansa datan uudelleen. Toisaalta käyttöliittymällä on suora yhteys pelin käyttämään tietokantaan, sillä käyttöliittymän näkymät tarvitsevat suoraa pääsyä käsiksi tietokantaan talletettuun tietoon esimerkiksi sovelluksen esittäessä _lopetussivulla_ pistemäärällisesti viiden parhaan pelaajan listauksen.

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä

Sovelluksen graafinen käyttöliittymä on toteutettu määrittelemällä lähes koko käyttöliittymän rakenne luokan [PongApplication](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/ui/PongApplication.java) metodissa [start](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/ui/PongApplication.java#L85). Sovelluksen eri näkymät rakentava koodi kannattaisi erottaa kutakin näkymää vastaaviksi metodeiksi tai luokiksi.
