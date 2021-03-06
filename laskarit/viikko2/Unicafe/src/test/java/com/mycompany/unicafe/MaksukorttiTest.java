package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikeinAlussa() {
        assertEquals("saldo: 0.10", kortti.toString());
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
