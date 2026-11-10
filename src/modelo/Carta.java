package modelo;

import java.util.Objects;

public class Carta {

    private final Palo palo;
    private final Valor valor;

    public Carta(Palo palo, Valor valor){
        if (palo == null){
            throw new IllegalArgumentException("El palo es requerido");
        }

        if (valor == null){
            throw new IllegalArgumentException("El valor es requerido");
        }

        this.valor = valor;
        this.palo = palo;
    }

    public Valor getValor(){
        return valor;
    }

    public Palo getPalo(){
        return palo;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Carta other = (Carta) obj;

        return Objects.equals(this.palo, other.palo)
                && this.valor == other.valor;
    }

    @Override
    public int hashCode(){
        return Objects.hash(palo, valor);
    }
}
