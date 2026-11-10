package modelo;

import java.util.ArrayList;
import java.util.List;

public class Baza {
    private static final int MAXIMO_JUGADAS = 4;
    private final List<Jugada> jugadas = new ArrayList<>();

    public void agregarJugada(Jugada jugada){
        if (jugada == null) throw new IllegalArgumentException("La jugada es requerida");

        if (estaCompleta()) {
            throw new IllegalArgumentException("El maximo de jugadas es" + MAXIMO_JUGADAS);
        }

        jugadas.add(jugada);
    }

    public boolean estaCompleta(){
        return jugadas.size() == MAXIMO_JUGADAS;
    }

    public Palo getPaloInicial(){
        if (jugadas.isEmpty()) throw new IllegalStateException("Aun no hay jugadas realizadas");
        return jugadas.getFirst().getCarta().getPalo();
    }

    public Jugador getGanador(){
        if (!estaCompleta()){
            throw new IllegalStateException("La baza aun no esta completa");
        }


        for (Jugada j : jugadas){
            Jugador actual = j.getJugador();


        }

        return null;
    }

    /** Puntos de las cartas jugadas, 1 por cada corazon, 13 por la Q de picas. */
    public int getPuntos(){
        int puntos = 0;

        for (Jugada j : jugadas){
            Carta c = j.getCarta();

            if (c.getPalo() == Palo.CORAZONES) puntos += 1;
            if (c.getValor() == Valor.Q) puntos += 13;
        }

        return puntos;
    }

    public List<Jugada> getJugadas(){
        return List.copyOf(jugadas);
    }
}
