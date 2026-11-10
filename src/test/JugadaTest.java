package test;

import modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JugadaTest {

    @Test
    void jugadaSinJugadorLanzaExcepcion(){
        assertThrows(IllegalArgumentException.class,
                ()-> new Jugada(null, new Carta(Palo.DIAMANTES, Valor.CUATRO)));
    }

    @Test
    void juagadaSinCartaLanzaExcepcion(){
        assertThrows(IllegalArgumentException.class,
                ()-> new Jugada(new Jugador("Juan"), null));
    }

    @Test
    void jugadaDevuelveCartaYJugador(){
        Jugador j = new Jugador("Juan");
        Carta c = new Carta(Palo.DIAMANTES, Valor.CUATRO);
        Jugada ju = new Jugada(j, c);

        assertEquals(c, ju.getCarta());
        assertEquals(j, ju.getJugador());
    }

}
