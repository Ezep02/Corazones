package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador {

    private final String nombre;
    private final List<Carta> mano = new ArrayList<>();

    public Jugador(String nombre){
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre es requerido");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirCartas(List<Carta> cartas){
        if(cartas == null){
            throw new IllegalArgumentException("Las cartas son requeridas");
        }
        mano.addAll(cartas);
    }

    public Carta jugarCarta(Carta carta){
        if(!mano.remove(carta)){
            throw new IllegalArgumentException("El jugador no tiene esa carta");
        }
        return carta;
    }

    public boolean tienePalo(Palo palo){
        for (Carta c : mano){
            if (c.getPalo() == palo) return true;
        }

        return false;
    }

    public int getCantidadDeCartas(){
        return mano.size();
    }

    public List<Carta> getMano(){
        return List.copyOf(mano);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Jugador otro = (Jugador) obj;

        return nombre.equals(otro.nombre);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre);
    }
}
