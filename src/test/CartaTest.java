package test;

import modelo.Carta;
import modelo.Palo;
import modelo.Valor;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CartaTest {
    @Test
    void cartasConMismoPaloYValorSonIguales(){
        Carta a = new Carta(Palo.TREBOLES, Valor.DOS);
        Carta b = new Carta(Palo.TREBOLES, Valor.DOS);

        assertEquals(a, b);
    }

    @Test
    void cartasConMismoPaloYValorNoSonIguales(){
        Carta a = new Carta(Palo.TREBOLES, Valor.DOS);
        Carta b = new Carta(Palo.TREBOLES, Valor.TRES);
        assertNotEquals(a, b);
    }

    @Test
    void cartasIgualesTienenMismoHashCode(){
        Carta a = new Carta(Palo.TREBOLES, Valor.DOS);
        Carta b = new Carta(Palo.TREBOLES, Valor.DOS);

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void unaCartaConValorNuloLanzaExecpcion(){
        assertThrows(IllegalArgumentException.class, () -> new Carta(Palo.TREBOLES, null));
    }

    @Test
    void unaCartaConPaloNuloLanzaExepcion(){
        assertThrows(IllegalArgumentException.class, () -> new Carta(null, Valor.TRES));
    }

    @Test
    void unaCartaNoEsIgualANull(){
        assertNotEquals(new Carta(Palo.TREBOLES, Valor.CINCO),(Object) null);
    }

    @Test
    void unaCartaNoEsIgualAUnString(){
        assertNotEquals(new Carta(Palo.TREBOLES, Valor.CINCO),(Object) "");
    }
}
