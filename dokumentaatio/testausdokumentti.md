# Testausdokumentti

Sovellusta testattiin niin automatisoiduin yksikkö- ja integraatiotestein JUnitilla kuin manuaalisesti järjestelmätason testeillä.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Huomattava enemmistö luoduista automatisoiduista testeistä koskee sovelluslogiikkaa edustavia pakkauksia _pong.domain_ ja _pong.logics_. Testiluokat [BallTest](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/test/java/pong/tests/domain/BallTest.java), [MovementTest](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/test/java/pong/tests/domain/MovementTest.java), [PaddleTest](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/test/java/pong/tests/domain/PaddleTest.java), [PlayerTest](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/test/java/pong/tests/domain/PlayerTest.java) ja [ScoreTest](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/test/java/pong/tests/domain/ScoreTest.java) käsittävät kukin nimensä osoittaman pelielementtiluokan yksikkötestejä, joiden tarkoituksena on varmistaa yksikkötason testien läpäisy. Sen sijaan pakkaukseen _pong.logics_ liittyvä testiluokka [PongLogicsTest](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/test/java/pong/tests/logics/PongLogicsTest.java) toimii kokoavana integraatiotestinä: testiluokka yhdistää eri pelielementtejä monimutkaisemmiksi toimintayksiköiksi ja seuraa niiden yhteistoiminnan onnistumista. Koska nimenomaan sovelluksen luokka [PongLogics](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/logics/PongLogics.java) vastaa pelin sovelluslogiikan toimivuudesta ja tapahtumien yhdistämisestä, on menettelytapa mielekäs.

### DAO ja tietokanta

Niin pakkauksen _pong.dao_ kuin pakkauksen _pong.database_ luokkia testataan sovelluslogiikasta erillään. Syynä tähän on se, että tietokannan ja siihen liittyvän tietokantataulun käsittely on sovelluksessa käyttöjärjestelmän vastuulla. Näin toimitaan, koska käyttöjärjestelmän saadessa suoraan tietoa tietokannalta ja DAO-toteutuksen tietokantakyselyiltä tallennettu tieto voidaan asettaa suoraviivaisesti käyttöjärjestelmän visuaalisiin komponentteihin ja ikkunanäkymään.

Testiluokat [DatabaseTest](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/test/java/pong/tests/database/DatabaseTest.java) ja [PlayerDaoTest](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/test/java/pong/tests/dao/PlayerDaoTest.java) operoivat testimielisen tietokannan _test.db_ avulla. Tietokantaan liittyvän testiluokan tehtävänä on tarkistaa, että toivottu tietokanta luodaan, mikäli se ei ole vielä olemassa, ja että mahdollisen alustuksen jälkeen tietokannassa on muun toiminnan kannalta välttämätön tietokantataulu. PlayerDaoTest-testiluokka puolestaan varmistaa, että tietokantaan tehtävät kyselyt antavat oikean ratkaisun. Testitietokannan ohella tätä simuloidaan testisyötteillä.

### Testikattavuus

Sovelluksen käyttöliittymäpakkausta ja siihen kuuluvaa [PongApplication](https://github.com/heidihas/otm-harjoitustyo/blob/master/Pong/src/main/java/pong/ui/PongApplication.java)-luokkaa lukuun ottamatta testauksen rivi- ja haarautumakattavuus on vastaavanlainen:

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_testikattavuus.png" width="800">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus suoritettiin manuaalisesti seuraamalla sovelluksen asennus- ja käyttöohjeita sekä vaatimusmäärittelyn tietoja ohjelman tarjoamasta sovelluslogiikasta.

### Asennus ja konfigurointi

Sovelluksen käyttöönottoa testattiin sekä OSX- että Linux-pohjaisessa ympäristössä noudattamalla sovelluksesta esitettyjä asennus- ja käyttöohjeita. Alun perin ohjelman suorittamisen edellytyksenä oli, että suoritettavan ohjelman käynnistyshakemistossa oli oltava valmis tietokantatiedosto ja aloitussivulla näytettävä kuvatiedosto. Lopullisessa versiossa tietokantatiedoston olemassaolo ei ole enää vaatimuksena, vaan sovellus luo ja alustaa sen tarvittaessa. Käyttöönottoa testattiin molemmissa edellä mainituissa tapauksissa.

### Toiminnallisuudet

Vaatimusmäärittelyssä ilmoitetut ja sovellukseen toteutetut toiminnallisuudet rakennettiin ohessa aktiivisesti kaikkea luotavaa testaten. Aloitussivulta käytiin läpi kaikki mieleen tulleet tilanteet ja syötevaihtoehdot. Samalla varmistettiin, ettei virheellistä syötettä - kuten tyhjää, vain välilyönneistä koostuvaa tai merkkimäärällisesti liian pitkää käyttäjänimeä - ole mahdollista tarjota ja päästä onnistuneesti jatkamaan pelinäkymään. Virheellisille syötteille luotiin kolme virheviestiä: _Maximum 8 characters_, _Choose different names_ ja _Choose level_. Vastaavasti pelinäkymässä testattiin, että kaikki pelin säännöt pallon kimpoamisesta pelin voittamiseen toimivat moitteettomasti. Lopetussivun osalta tarkistettiin, että kolme nappia _Re-start_, _New game_ ja _End game_ toimivat kukin oletetusti. Esimerkiksi siten, että uuden pelikierroksen alkaessa pallon etenemisnopeus on taas alustettu valitun vaikeustason mukaisesti eikä pallo jatka sillä nopeudella, jonka se edellisellä pelikierroksella ehti saavuttaa.

## Testauksen tulos jatkokehittelystä

Vaikka pitkälti pyrittiin toimimaan niin, että testauksen saatossa esiin tulleet epäkohdat ratkaistiin, jäi sovellukseen vielä jotain kehitettävää. Tämä koskee eritoten pelinäkymän sovelluslogiikkaa, pallon ja mailojen vuorovaikutusta. Koska pallon ja mailojen välisen sovelluslogiikan toteuttaminen ei aluksi ollut koodikielen tasolla niin ilmeistä, tapahtui pelisääntöjen realisoiminen pitkälti käsi kädessä kokeilun ja testaamisen kanssa. Lopullisessa versiossa pallo kimpoaa niin horisontaalisesti kuin vertikaalisesti vastakkaiseen suuntaan pallon lähestyessä mailaa ylä- tai alapuolelta ja ainoastaan horisontaalisesti vastakkaiseen suuntaan pallon osuessa mailan pystysuoraan sivuun. Kuitenkin testaamisen lomassa kävi ilmi, että säännönmukainen kimpoaminen ei tapahdu kaikissa tilanteissa, vaan pallo ikään kuin jää hetkeksi jumiin mailan kohdalle. Kyseistä bugia pyrittiin moneen kertaan ratkaisemaan, mutta se on edelleen aika ajoin läsnä. Bugin taustalla saattaisi olla toisaalta jokin virhetapahtuma, joka syntyy sovelluksen käytön (mailojen liikuttaminen) ja pelin piirto-ominaisuuksien välisestä kommunikaatioviiveestä.
