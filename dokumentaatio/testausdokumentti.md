# Testausdokumentti

Sovellusta on testattu niin automatisoiduin yksikkö- ja integraatiotestein JUnitilla kuin manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Huomattava enemmistö luoduista automatisoiduista testeistä koskee sovelluslogiikkaa edustavia pakkauksia pong.domain ja pong.logics. Testiluokat BallTest, MovementTest, PaddleTest, PlayerTest ja ScoreTest käsittävät kukin nimensä osoittaman pelielementtiluokan yksikkötestejä, joiden tarkoituksena on varmistaa alimman vaativuustason testien läpäisy. Sen sijaan pakkaukseen pong.logics liittyvä testiluokka PongLogicsTest toimii kokoavana integraatiotestinä: testiluokka yhdistää eri pelielementtejä monimutkaisemmiksi toimintayksiköiksi ja seuraa niiden yhteistoiminnan onnistumista. Koska nimenomaan sovelluksen luokka PongLogics vastaa sovelluslogiikan toimivuudesta ja tapahtumien yhdistämisestä, on menettelytapa mielekäs.

### DAO ja tietokanta



### Testikattavuus

Sovelluksen käyttöliittymäpakkausta ja siihen kuuluvaa PongApplication-luokkaa lukuun ottamatta testauksen rivi- ja haarautumakattavuus on vastaavanlainen:

<img src="https://github.com/heidihas/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/Pong_testikattavuus.png" width="800">

## Järjestelmätestaus

### Asennus ja konfigurointi

### Toiminnallisuudet

## Sovellukseen jääneet laatuongelmat
