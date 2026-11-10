package modelo;

public class Jugada {

    private final Carta carta;
    private final Jugador jugador;

    public Jugada(Jugador jugador, Carta carta){

        if (jugador == null){
            throw new IllegalArgumentException("El jugador es requerido");
        }

        if (carta == null){
            throw new IllegalArgumentException("La carta es requerida");
        }

        this.jugador = jugador;
        this.carta = carta;
    }

    public Carta getCarta() {
        return carta;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
