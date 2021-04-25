package dominio;

public class Posicion {

    public Integer i;
    public Integer j;

    public Posicion(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }

    /**
     * Calcula la distancia entre dos puntos A y B del escenario (No necesariamente alineados).
     *
     * @param unaPosicion  punto A.
     * @param otraPosicion punto B.
     * @return distancia entre ambos puntos.
     */
    public static Double distanciaEntre(Posicion unaPosicion, Posicion otraPosicion) {
        return Math.sqrt(Math.pow(otraPosicion.i - unaPosicion.i, 2) + Math.pow(otraPosicion.j - unaPosicion.j, 2));
    }

    public Boolean equals(Posicion otraPosicion) {
        return (this.i.equals(otraPosicion.i) && this.j.equals(otraPosicion.j));
    }

    public Posicion clone() {
        return new Posicion(this.i, this.j);
    }

}
