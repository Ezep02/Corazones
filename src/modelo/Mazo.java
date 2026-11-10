package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Mazo {
    private final List<Carta> cartas = new ArrayList<>();
    private final Random random;

    public Mazo(Random random){
        if (random == null){
            throw new IllegalArgumentException("El numero de combinaciones es requerido");
        }

        this.random = random;

        for (Palo p : Palo.values()){
            for (Valor v : Valor.values()){
                cartas.add(new Carta(p, v));
            }
        }
    }

    public void mezclar(){
        Collections.shuffle(cartas, random);
    }

    public List<List<Carta>> repartir(int numeroDeManos, int cantidadDeCartas){

        if (numeroDeManos <= 0 || cantidadDeCartas <= 0){
            throw new IllegalArgumentException("Las manos y las cartas deben ser positivas");
        }

        if (numeroDeManos * cantidadDeCartas > cartas.size()){
            throw new IllegalArgumentException("No hay cartas suficientes en el mazo");
        }

        List<List<Carta>> manos = new ArrayList<>();
        for (int i = 0; i < numeroDeManos; i++){
            List<Carta> mano = new ArrayList<>();
            for (int j = 0; j < cantidadDeCartas; j++){
                mano.add(cartas.removeLast());
            }

            manos.add(mano);
        }

        return manos;
    }

    public int getCantidadDeCartas(){
        return cartas.size();
    }

}
