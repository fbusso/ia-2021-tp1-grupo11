package dominio;

import java.util.Objects;

public class Posicion {

    public Integer i;
    public Integer j;

    public Posicion(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }

    public static Double distanciaEntre(Posicion unaPosicion, Posicion otraPosicion) {
        return unaPosicion.i.equals(otraPosicion.i)
                ? (double) Math.abs(unaPosicion.j - otraPosicion.j)
                : (double) Math.abs(unaPosicion.i - otraPosicion.i);
    }

    @Override
    public boolean equals(Object otraPosicion) {
        return (otraPosicion instanceof Posicion)
                && this.i.equals(((Posicion) otraPosicion).j)
                && this.j.equals(((Posicion) otraPosicion).j);
    }

    public Posicion clone() {
        return new Posicion(this.i, this.j);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
