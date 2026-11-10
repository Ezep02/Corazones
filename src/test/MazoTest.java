package test;

import modelo.Carta;
import modelo.Mazo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MazoTest {

    @Test
    void mazoRecienCreadoTiene52Cartas(){
        Mazo mazo = new Mazo(new Random(13));
        assertEquals(52, mazo.getCantidadDeCartas());
    }

    @Test
    void mazoRecienCreadoNoTieneCartasRepetidas(){
        Mazo mazo = new Mazo(new Random(13));
        List<Carta> mano = mazo.repartir(1, 52).getFirst();
        Set<Carta> sinRepetidas = new HashSet<>(mano);

        assertEquals(52, sinRepetidas.size());
    }

    @Test
    void mazoNoPuedeCrearseSinRandom(){
        assertThrows(IllegalArgumentException.class, ()-> new Mazo(null));
    }

    @Test
    void repartirMasCartasDeLasQueHayLanzaExcepcion(){
        Mazo mazo = new Mazo(new Random(13));
        assertThrows(IllegalArgumentException.class, ()-> mazo.repartir(4, 14) );
    }

    @Test
    void mezclarConLaMismaSemillaDaElMismoOrden(){
        Mazo mazo1 = new Mazo(new Random(13));
        Mazo mazo2 = new Mazo(new Random(13));

        mazo1.mezclar();
        mazo2.mezclar();

        List<List<Carta>> manoMazo1 = mazo1.repartir(4, 13);
        List<List<Carta>> manoMazo2 = mazo2.repartir(4, 13);
        assertEquals(manoMazo1, manoMazo2);
    }

    @Test
    void mezclarCambiaElOrdenDeLasCartas(){
        Mazo mezclado = new Mazo(new Random(13));
        Mazo sinMezclar = new Mazo(new Random(13));

        mezclado.mezclar();

        assertNotEquals(mezclado.repartir(4, 13), sinMezclar.repartir(4, 13));
    }

    @Test
    void repartirDevuelveLaCantidadDeManosPedida(){
        Mazo mazo = new Mazo(new Random(13));
        List<List<Carta>> manos = mazo.repartir(4, 13);
        assertEquals(4, manos.size());
    }

    @Test
    void cadaManoRepartidaTieneLaCantidadDeCartasPedida(){
        Mazo mazo = new Mazo(new Random(13));
        for (List<Carta> mano : mazo.repartir(4, 13)){
            assertEquals(13, mano.size());
        }
    }

    @Test
    void repartirRetiraLasCartasDelMazo(){
        Mazo mazo = new Mazo(new Random(13));
        mazo.repartir(4, 10);
        assertEquals(12, mazo.getCantidadDeCartas());
    }
}
