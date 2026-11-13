package modelo;

import java.util.ArrayList;
import java.util.List;

public class Mano {

    private static final int BAZAS_POR_MANO = 13;
    private final List<Baza> bazasJugadas = new ArrayList<>();

    public void agregarBaza(Baza baza){

        if (baza == null){
            throw new IllegalArgumentException("La baza es requerida");
        }

        if (!baza.estaCompleta()){
            throw new IllegalArgumentException("La baza aun no esta completa");
        }

        if (bazasJugadas.size() == BAZAS_POR_MANO){
            throw new IllegalStateException("No es posible agregar mas bazas, el maximo es "+BAZAS_POR_MANO);
        }

        bazasJugadas.add(baza);
    }

    public boolean corazonesRotos(){

        for (Baza b : bazasJugadas){
            if (b.tieneCorazones()) return true;
        }

        return false;
    }

    public int getCantidadDeBazas(){
        return bazasJugadas.size();
    }

    public boolean estaTerminada(){
        return bazasJugadas.size() == BAZAS_POR_MANO;
    }
}
