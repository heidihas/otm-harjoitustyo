# Testausdokumentti

Sovellusta testattiin niin automatisoiduin yksikkö- ja integraatiotestein JUnitilla kuin manuaalisesti järjestelmätason testeillä.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Huomattava enemmistö luoduista automatisoiduista testeistä koskee sovelluslogiikkaa edustavia pakkauksia pong.domain ja pong.logics. Testiluokat BallTest, MovementTest, PaddleTest, PlayerTest ja ScoreTest käsittävät kukin nimensä osoittaman pelielementtiluokan yksikkötestejä, joiden tarkoituksena on varmistaa yksikkötason testien läpäisy. Sen sijaan pakkaukseen pong.logics liittyvä testiluokka PongLogicsTest toimii kokoavana integraatiotestinä: testiluokka yhdistää eri pelielementtejä monimutkaisemmiksi toimintayksiköiksi ja seuraa niiden yhteistoiminnan onnistumista. Koska nimenomaan sovelluksen luokka PongLogics vastaa pelin sovelluslogiikan toimivuudesta ja tapahtumien yhdistämisestä, on menettelytapa mielekäs.

### DAO ja tietokanta

Niin pakkauksen pong.dao kuin pakkauksen pong.database luokkia testataan sovelluslogiikasta erillään. Syynä tähän on se, että tietokannan ja siihen liittyvän tietokantataulun käsittely on sovelluksessa käyttöjärjestelmän vastuulla. Näin toimitaan, koska käyttöjärjestelmän saadessa suoraan tietoa tietokannalta ja DAO-toteutuksen tietokantakyselyiltä tallennettu tieto voidaan asettaa suoraviivaisesti käyttöjärjestelmän visuaalisiin komponentteihin ja ikkunanäkymään.

Testiluokat DatabaseTest ja PlayerDaoTest operoivat testimielisen tietokannan _test.db_ avulla. Tietokantaan liittyvän testiluokan tehtävänä on tarkistaa, että toivottu tietokanta luodaan, mikäli se ei ole vielä olemassa, ja että mahdollisen alustuksen jälkeen tietokannassa on muun toiminnan kannalta välttämätön tietokantataulu. PlayerDaoTest-testiluokka puolestaan varmistaa, että tietokantaan tehtävät kyselyt antavat oikean ratkaisun. Testitietokannan ohella tätä simuloidaan testisyötteillä.

### Testikattavuus

Sovelluksen käyttöliittymäpakkausta ja siihen kuuluvaa PongApplication-luokkaa lukuun ottamatta testauksen rivi- ja haarautumakattavuus on vastaavanlainen:

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_testikattavuus.png" width="800">

## Järjestelmätestaus

Sovelluksen järjestelmätestaus suoritettiin manuaalisesti seuraamalla sovelluksen asennus- ja käyttöohjeita sekä vaatimusmäärittelyn tietoja ohjelman tarjoamasta sovelluslogiikasta.

### Asennus ja konfigurointi

Sovelluksen käyttöönottoa testattiin sekä OSX- että Linux-pohjaisessa ympäristössä noudattamalla sovelluksesta esitettyjä asennus- ja käyttöohjeita. Alun perin ohjelman suorittamisen edellytyksenä oli, että suoritettavan ohjelman käynnistyshakemistossa oli oltava valmis tietokantatiedosto ja aloitussivulla näytettävä kuvatiedosto. Lopullisessa versiossa tietokantatiedoston olemassaolo ei ole enää vaatimuksena, vaan sovellus luo ja alustaa sen tarvittaessa. Käyttöönottoa testattiin molemmissa edellä mainituissa tapauksissa.

### Toiminnallisuudet

Vaatimusmäärittelyssä 

## Testauksen tulos jatkokehittelystä

Vaikka pitkälti pyrittiin toimimaan niin, että testauksen saatossa esiin tulleet epäkohdat ratkaistiin, jäi sovellukseen vielä jotain kehitettävää. Tämä koskee eritoten pelinäkymän sovelluslogiikkaa, pallon ja mailojen vuorovaikutusta. Koska pallon ja mailojen välisen sovelluslogiikan toteuttaminen ei aluksi ollut koodikielen tasolla niin ilmeistä, tapahtui pelisääntöjen realisoiminen pitkälti käsi kädessä kokeilun ja testaamisen kanssa. Lopullisessa versiossa pallo kimpoaa niin horisontaalisesti kuin vertikaalisesti vastakkaiseen suuntaan pallon lähestyessä mailaa ylä- tai alapuolelta ja ainoastaan horisontaalisesti vastakkaiseen suuntaan pallon osuessa mailan pystysuoraan sivuun. Kuitenkin testaamisen lomassa kävi ilmi, että säännönmukainen kimpoaminen ei tapahdu kaikissa tilanteissa, vaan pallo ikään kuin jää hetkeksi jumiin mailan kohdalle. Kyseistä bugia pyrittiin moneen kertaan ratkaisemaan, mutta se on edelleen aika ajoin läsnä. Bugin taustalla saattaisi olla toisaalta jokin virhetapahtuma, joka syntyy sovelluksen käytön (mailojen liikuttaminen) ja pelin piirto-ominaisuuksien välisestä kommunikaatioviiveestä.
