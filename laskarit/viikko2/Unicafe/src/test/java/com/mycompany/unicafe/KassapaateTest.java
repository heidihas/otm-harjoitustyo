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

    @Before
    public void setUp() {
        paate = new Kassapaate();    
    }
    
    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(paate!=null);      
    }
    
    @Test
    public void kassaRahaaOlemassa() {
        assertEquals(1000, paate.kassassaRahaa());
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
    public void lataaminenOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void ottaminenSaldoaTarpeeksi() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }
    
    @Test
    public void ottaminenRahaaEiTarpeeksi() {
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahatRiittavat() {
        assertEquals(true, kortti.otaRahaa(5));
    }
    
    @Test
    public void rahatEivatRiita() {
        assertEquals(false, kortti.otaRahaa(20));
    }
}
