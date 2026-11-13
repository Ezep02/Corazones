package test;

import modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BazaTest {

    @Test
    void laDamaDePicasValeTrece(){
        Baza baza = new Baza();
        Jugador j = new Jugador("Juan");
        baza.agregarJugada(new Jugada(j, new Carta(Palo.PICAS, Valor.Q)));

        assertEquals(13, baza.getPuntos());
    }

    @Test
    void laDamaDeCorazonesValeUnPunto(){
        Baza baza = new Baza();
        Jugador j = new Jugador("Juan");
        baza.agregarJugada(new Jugada(j, new Carta(Palo.CORAZONES, Valor.Q)));

        assertEquals(1, baza.getPuntos());
    }

    @Test
    void laDamaDeDiamantesValeCero(){
        Baza baza = new Baza();
        Jugador j = new Jugador("Juan");
        baza.agregarJugada(new Jugada(j, new Carta(Palo.DIAMANTES, Valor.Q)));

        assertEquals(0, baza.getPuntos());
    }

    @Test
    void jugarVariosCorazonesDebenSumar(){
        Baza baza = new Baza();

        Jugador j1 = new Jugador("Juan");
        Jugador j2 = new Jugador("Mica");

        baza.agregarJugada(new Jugada(j1, new Carta(Palo.CORAZONES, Valor.Q)));
        baza.agregarJugada(new Jugada(j2, new Carta(Palo.CORAZONES, Valor.CUATRO)));

        assertEquals(2, baza.getPuntos());
    }

    @Test
    void unJugadorNoPuedeJugarDosVecesEnLaMismaBaza(){
        Baza baza = new Baza();
        Jugador j = new Jugador("Juan");

        baza.agregarJugada(new Jugada(j, new Carta(Palo.PICAS, Valor.K)));

        assertThrows(IllegalArgumentException.class, () -> baza.agregarJugada(
                new Jugada(j, new Carta(Palo.PICAS, Valor.Q))
        ));

        assertEquals(1, baza.getJugadas().size());
    }

    @Test
    void unaCartaMasAltaDeOtroPaloNoGana(){
        Baza baza = new Baza();

        Jugada[] jugadas = {
                new Jugada(new Jugador("Juan"), new Carta(Palo.TREBOLES, Valor.CINCO)),
                new Jugada(new Jugador("Mica"), new Carta(Palo.PICAS, Valor.AS)),
                new Jugada(new Jugador("Ana"), new Carta(Palo.TREBOLES, Valor.NUEVE)),
                new Jugada(new Jugador("Leo"), new Carta(Palo.TREBOLES, Valor.DOS)),
        };

        for (Jugada j : jugadas){
            baza.agregarJugada(j);
        }

        assertEquals("Ana", baza.getGanador().getNombre());
    }

    @Test
    void ganaLaCartaMasAltaDelPaloInicial(){
        Baza baza = new Baza();

        Jugada[] jugadas = {
                new Jugada(new Jugador("Juan"), new Carta(Palo.TREBOLES, Valor.CINCO)),
                new Jugada(new Jugador("Mica"), new Carta(Palo.TREBOLES, Valor.K)),
                new Jugada(new Jugador("Ana"), new Carta(Palo.TREBOLES, Valor.NUEVE)),
                new Jugada(new Jugador("Leo"), new Carta(Palo.TREBOLES, Valor.DOS)),
        };

        for (Jugada j : jugadas){
            baza.agregarJugada(j);
        }

        assertEquals("Mica", baza.getGanador().getNombre());
    }

    @Test
    void bazaIncompletaNoTieneGanador(){
        Baza baza = new Baza();

        Jugada[] jugadas = {
                new Jugada(new Jugador("Juan"), new Carta(Palo.TREBOLES, Valor.CINCO)),
                new Jugada(new Jugador("Mica"), new Carta(Palo.TREBOLES, Valor.K)),
                new Jugada(new Jugador("Ana"), new Carta(Palo.TREBOLES, Valor.NUEVE)),
        };

        for (Jugada j : jugadas){
            baza.agregarJugada(j);
        }

        assertThrows(IllegalStateException.class, baza::getGanador);
    }

    @Test
    void unaBazaVaciaNoTienePaloInicial(){
        Baza baza = new Baza();
        assertThrows(IllegalStateException.class, baza::getPaloInicial);
    }

    @Test
    void bazaConCuatroJugadasEstaCompleta(){
        Baza baza = new Baza();

        Jugada[] jugadas = {
                new Jugada(new Jugador("Juan"), new Carta(Palo.TREBOLES, Valor.CINCO)),
                new Jugada(new Jugador("Mica"), new Carta(Palo.TREBOLES, Valor.K)),
                new Jugada(new Jugador("Ana"), new Carta(Palo.TREBOLES, Valor.NUEVE)),
                new Jugada(new Jugador("Leo"), new Carta(Palo.TREBOLES, Valor.DOS)),
        };

        for (Jugada j : jugadas){
            baza.agregarJugada(j);
        }

        assertTrue(baza.estaCompleta());
    }

    @Test
    void unaBazaCompletaNoAceptaUnaQuintaJugada(){
        Baza baza = new Baza();

        Jugada[] jugadas = {
                new Jugada(new Jugador("Juan"), new Carta(Palo.TREBOLES, Valor.CINCO)),
                new Jugada(new Jugador("Mica"), new Carta(Palo.TREBOLES, Valor.K)),
                new Jugada(new Jugador("Ana"), new Carta(Palo.TREBOLES, Valor.NUEVE)),
                new Jugada(new Jugador("Leo"), new Carta(Palo.TREBOLES, Valor.DOS)),
        };

        for (Jugada j : jugadas){
            baza.agregarJugada(j);
        }

        assertThrows(IllegalStateException.class,
                ()-> baza.agregarJugada(new Jugada(new Jugador("Pedro"), new Carta(Palo.TREBOLES, Valor.TRES))));
        assertEquals(4, baza.getJugadas().size());
    }

    @Test
    void unaBazaVaciaNoTieneCorazones(){
        Baza baza = new Baza();
        assertFalse(baza.tieneCorazones());
    }

    @Test
    void unaBazaConUnCorazonTieneCorazones(){
        Baza baza = new Baza();
        baza.agregarJugada(new Jugada(new Jugador("Juan"), new Carta(Palo.CORAZONES, Valor.CINCO)));
        assertTrue(baza.tieneCorazones());
    }

    @Test
    void unaBazaSinCorazonesNoTieneCorazones(){
        Baza baza = new Baza();
        baza.agregarJugada(new Jugada(new Jugador("Juan"), new Carta(Palo.PICAS, Valor.CINCO)));
        assertFalse(baza.tieneCorazones());
    }

    @Test
    void unaBazaConLaDamaDePicasSinCorazonesNoTieneCorazones() {
        Baza baza = new Baza();
        baza.agregarJugada(new Jugada(new Jugador("Juan"), new Carta(Palo.PICAS, Valor.Q)));
        assertFalse(baza.tieneCorazones());
    }
}
