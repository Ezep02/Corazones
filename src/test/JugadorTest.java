package test;

import modelo.Carta;
import modelo.Jugador;
import modelo.Palo;
import modelo.Valor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {
    @Test
    void jugadorSinNombreLanzaExcepcion(){
        assertThrows(IllegalArgumentException.class, () -> new Jugador(" "));
    }

    @Test
    void jugadorSinNombreDevuelveExepcion(){
        assertThrows(IllegalArgumentException.class,
                ()-> new Jugador(null));
    }

    @Test
    void jugadorRecibeCartaNulaDevuelveExepcion(){
        Jugador j = new Jugador("Ana");
        assertThrows(IllegalArgumentException.class,
                ()-> j.recibirCartas(null));
    }

    @Test
    void jugadorRecibeLasCartasRepartidas(){
        Jugador j = new Jugador("Ana");
        j.recibirCartas(List.of(new Carta(Palo.PICAS, Valor.AS),
                new Carta(Palo.CORAZONES, Valor.DOS)));
        assertEquals(2, j.getCantidadDeCartas());
    }

    @Test
    void jugarCartaQueJugadorNoTieneLanzaExcepcion(){
        Jugador j = new Jugador("Juan");
        Carta c = new Carta(Palo.TREBOLES, Valor.CINCO);
        j.recibirCartas(List.of(c));
        assertThrows(IllegalArgumentException.class,
                ()-> j.jugarCarta(new Carta(Palo.DIAMANTES, Valor.CUATRO)));
        assertEquals(1, j.getCantidadDeCartas());
    }

    @Test
    void jugarCartaDevuelveLaCartaYLaSacaDeLaMano(){
        Jugador j = new Jugador("Juan");
        Carta c = new Carta(Palo.TREBOLES, Valor.CINCO);
        j.recibirCartas(List.of(c));

        assertEquals(c, j.jugarCarta(c));
        assertEquals(0, j.getCantidadDeCartas());
    }

    @Test
    void jugadorTienePaloDevuelveTrue(){
        Jugador j = new Jugador("Juan");
        Carta c = new Carta(Palo.TREBOLES, Valor.CINCO);
        j.recibirCartas(List.of(c));
        assertTrue(j.tienePalo(Palo.TREBOLES));
    }

    @Test
    void jugadorNoTienePaloDevuelveFalse(){
        Jugador j = new Jugador("Juan");
        Carta c = new Carta(Palo.TREBOLES, Valor.CINCO);
        j.recibirCartas(List.of(c));
        assertFalse(j.tienePalo(Palo.PICAS));
    }

    @Test
    void modificarManoLanzaExepcion(){
        Jugador j = new Jugador("Juan");
        Carta c = new Carta(Palo.TREBOLES, Valor.CINCO);
        j.recibirCartas(List.of(c));

        List<Carta> mano = j.getMano();
        assertThrows(UnsupportedOperationException.class,
                ()-> mano.remove(c));
    }

    @Test
    void jugadoresConMismoNombreSonIguales(){
        Jugador j1 = new Jugador("Mica");
        Carta c1 = new Carta(Palo.TREBOLES, Valor.CINCO);
        j1.recibirCartas(List.of(c1));

        Jugador j2 = new Jugador("Mica");
        Carta c2 = new Carta(Palo.CORAZONES, Valor.CINCO);
        j2.recibirCartas(List.of(c2));

        assertEquals(j1, j2);
        assertEquals(j1.hashCode(), j2.hashCode());
    }

    @Test
    void jugadoresConDistintoNombreNoSonIguales(){
        Jugador j1 = new Jugador("Juan");
        Carta c1 = new Carta(Palo.TREBOLES, Valor.CINCO);
        j1.recibirCartas(List.of(c1));

        Jugador j2 = new Jugador("Mica");
        Carta c2 = new Carta(Palo.CORAZONES, Valor.CINCO);
        j2.recibirCartas(List.of(c2));

        assertNotEquals(j1, j2);
    }
}
