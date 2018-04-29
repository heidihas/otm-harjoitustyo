# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa viisitasoista kerrosarkkitehtuuria. Alla oleva kuva osoittaa koodin pakkausrakenteen.

![Pakkauskaavion kuva](https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_pakkauskaavio.png)

Pakkaus _pong.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _pong.logics_ pelin säännöt ja logiikan, _pong.domain_ muun sovelluslogiikan pelielementteineen, _pong.dao_ tietojen pysyväistallennuksesta vastaavan koodin ja _pong.database_ yhteyden sovellukseen kuuluvaan tietokantaan.

## Käyttöliittymä

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
