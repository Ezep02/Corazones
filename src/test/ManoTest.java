package test;

import modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManoTest {

    private Baza bazaCon(Carta... cartas){
        Baza baza = new Baza();
        for (int i = 0; i < cartas.length; i++){
            baza.agregarJugada(new Jugada(new Jugador("J" + i), cartas[i]));
        }
        return baza;
    }

    private Baza bazaSinCorazones(){
        return bazaCon(
                new Carta(Palo.PICAS, Valor.CUATRO),
                new Carta(Palo.PICAS, Valor.CINCO),
                new Carta(Palo.TREBOLES, Valor.DOS),
                new Carta(Palo.DIAMANTES, Valor.AS));
    }

    private Baza bazaConCorazones(){
        return bazaCon(
                new Carta(Palo.CORAZONES, Valor.CUATRO),
                new Carta(Palo.PICAS, Valor.CINCO),
                new Carta(Palo.TREBOLES, Valor.DOS),
                new Carta(Palo.DIAMANTES, Valor.AS));
    }

    @Test
    void unaManoNuevaNoTieneCorazonesRotos(){
        Mano mano = new Mano();
        assertFalse(mano.corazonesRotos());
    }

    @Test
    void unaManoConBazasSinCorazonesNoTieneCorazonesRotos(){
        Mano mano = new Mano();
        mano.agregarBaza(bazaSinCorazones());
        assertFalse(mano.corazonesRotos());
    }

    @Test
    void unaManoConCorazonesEnUnaBazaPosteriorTieneCorazonesRotos(){
        Mano mano = new Mano();
        mano.agregarBaza(bazaSinCorazones());
        mano.agregarBaza(bazaConCorazones());
        assertTrue(mano.corazonesRotos());
    }

    @Test
    void agregarBazaNulaLanzaExcepcion(){
        Mano mano = new Mano();
        assertThrows(IllegalArgumentException.class, () -> mano.agregarBaza(null));
        assertEquals(0, mano.getCantidadDeBazas());
    }

    @Test
    void agregarBazaIncompletaLanzaExcepcion(){
        Mano mano = new Mano();
        Baza incompleta = bazaCon(
                new Carta(Palo.PICAS, Valor.CUATRO),
                new Carta(Palo.TREBOLES, Valor.DOS));

        assertThrows(IllegalArgumentException.class, () -> mano.agregarBaza(incompleta));
        assertEquals(0, mano.getCantidadDeBazas());
    }

    @Test
    void unaManoNoAceptaMasDeTreceBazas(){
        Mano mano = new Mano();
        for (int i=0; i < 13; i++){
            mano.agregarBaza(bazaSinCorazones());
        }

        assertThrows(IllegalStateException.class, ()-> mano.agregarBaza(bazaSinCorazones()));
        assertEquals(13, mano.getCantidadDeBazas());
    }

    @Test
    void unaManoNuevaNoEstaTerminada(){
        Mano mano = new Mano();
        assertFalse(mano.estaTerminada());
    }

    @Test
    void unaManoConDoceBazasNoEstaTerminada(){
        Mano mano = new Mano();
        for (int i=0; i < 12; i++){
            mano.agregarBaza(bazaSinCorazones());
        }
        assertFalse(mano.estaTerminada());
        assertEquals(12, mano.getCantidadDeBazas());
    }

    @Test
    void unaManoConTreceBazasEstaTerminada(){
        Mano mano = new Mano();
        for (int i=0; i < 13; i++){
            mano.agregarBaza(bazaSinCorazones());
        }
        assertTrue(mano.estaTerminada());
        assertEquals(13, mano.getCantidadDeBazas());
    }
}

