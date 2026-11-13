package modelo;

import java.util.ArrayList;
import java.util.List;

public class Baza {
    private static final int MAXIMO_JUGADAS = 4;
    private static final int PUNTOS_DAMA_DE_PICAS = 13;
    private static final int PUNTOS_POR_CORAZONES = 1;

    private final List<Jugada> jugadas = new ArrayList<>();

    public void agregarJugada(Jugada jugada){
        if (jugada == null) throw new IllegalArgumentException("La jugada es requerida");

        if (estaCompleta()) {
            throw new IllegalStateException("El maximo de jugadas es " + MAXIMO_JUGADAS);
        }

        for (Jugada j : jugadas){
            if (j.getJugador().equals(jugada.getJugador())){
                throw new IllegalArgumentException("Solo se permite un turno por jugador");
            }
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

        Palo paloInicial = getPaloInicial();
        Jugada ganadora = jugadas.getFirst();

        for (Jugada j : jugadas){
            if (j.getCarta().getPalo() == paloInicial
                    && j.getCarta().getValor().compareTo(ganadora.getCarta().getValor()) > 0){
                ganadora = j;
            }
        }

        return ganadora.getJugador();
    }

    /** Puntos de las cartas jugadas, 1 por cada corazon, 13 por la Q de picas. */
    public int getPuntos(){
        int puntos = 0;

        for (Jugada j : jugadas){
            Carta c = j.getCarta();

            if (c.getPalo() == Palo.CORAZONES) puntos += PUNTOS_POR_CORAZONES;
            if (c.getValor() == Valor.Q && c.getPalo() == Palo.PICAS) puntos += PUNTOS_DAMA_DE_PICAS;
        }

        return puntos;
    }

    public boolean tieneCorazones(){
        for (Jugada j : jugadas){
            if (j.getCarta().getPalo() == Palo.CORAZONES){
                return true;
            }
        }

        return false;
    }

    public List<Jugada> getJugadas(){
        return List.copyOf(jugadas);
    }
}
