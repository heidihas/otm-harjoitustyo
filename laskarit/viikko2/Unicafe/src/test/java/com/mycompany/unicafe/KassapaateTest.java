/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Heidi
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(400);
    }
    
    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(paate!=null);      
    }
    
    @Test
    public void kassaRahaaOlemassa() {
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void edullisiaEiAlussa() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaEiAlussa() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksuRiittavaEdullisestiLounaidenMaara() {
        paate.syoEdullisesti(240);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maksuRiittavaEdullisestiRahaKassassa() {
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void maksuRiittavaEdullisestiVaihtoraha() {
        assertEquals(0, paate.syoEdullisesti(240));
    }
    
    @Test
    public void maksuRiittavaMaukkaastiLounaidenMaara() {
        paate.syoMaukkaasti(400);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksuRiittavaMaukkaastiRahaKassassa() {
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void maksuRiittavaMaukkaastiVaihtoraha() {
        assertEquals(0, paate.syoMaukkaasti(400));
    }
    
    @Test
    public void maksuEiRiittavaEdullisestiLounaidenMaara() {
        paate.syoEdullisesti(40);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maksuEiRiittavaEdullisestiRahaKassassa() {
        paate.syoEdullisesti(40);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void maksuEiRiittavaEdullisestiVaihtoraha() {
        assertEquals(40, paate.syoEdullisesti(40));
    }
    
    @Test
    public void maksuEiRiittavaMaukkaastiLounaidenMaara() {
        paate.syoMaukkaasti(40);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksuEiRiittavaMaukkaastiRahaKassassa() {
        paate.syoMaukkaasti(40);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void maksuEiRiittavaMaukkaastiVaihtoraha() {
        assertEquals(40, paate.syoMaukkaasti(40));
    }
    
    @Test
    public void lataaminenOikein() {
        paate.lataaRahaaKortille(kortti, 10);
        assertEquals("saldo: 4.10", kortti.toString());
    }
    
    @Test
    public void lataaminenOikeinKassa() {
        paate.lataaRahaaKortille(kortti, 10);
        assertEquals(100010, paate.kassassaRahaa());
    }
    
    @Test
    public void lataaminenEiOikein() {
        paate.lataaRahaaKortille(kortti, -1);
        assertEquals("saldo: 4.0", kortti.toString());
    }
    
    @Test
    public void lataaminenEiOikeinKassa() {
        paate.lataaRahaaKortille(kortti, -1);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void rahatRiittavatEdullisesti() {
        assertEquals(true, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void rahatRiittavatEdullisestiKortti() {
        paate.syoEdullisesti(kortti);
        assertEquals("saldo: 1.60", kortti.toString());
    }
    
    @Test
    public void rahatRiittavatEdullisestiKassa() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void rahatRiittavatMaukkaasti() {
        assertEquals(true, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void rahatRiittavatMaukkaastiKortti() {
        paate.syoMaukkaasti(kortti);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void rahatRiittavatMaukkaastiKassa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void rahatEivatRiitaEdullisesti() {
        paate.syoEdullisesti(kortti);
        assertEquals(false, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void rahatEivatRiitaEdullisestiKortti() {
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals("saldo: 1.60", kortti.toString());
    }
    
    @Test
    public void rahatEivatRiitaEdullisestiKassa() {
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void rahatEivatRiitaMaukkaasti() {
        paate.syoMaukkaasti(kortti);
        assertEquals(false, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void rahatEivatRiitaMaukkaastiKortti() {
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void rahatEivatRiitaMaukkaastiKassa() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiMuutuKortillaEdullisesti() {
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kassaEiMuutuKortillaMaukkaasti() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
}
