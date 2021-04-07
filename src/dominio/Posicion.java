package dominio;

public class Posicion {

    public Integer i;
    public Integer j;

    public Posicion(Integer i, Integer j) {
        this.i = i;
        this.j = j;
    }

    public Boolean equals(Posicion otraPosicion) {
        return (this.i.equals(otraPosicion.i) && this.j.equals(otraPosicion.j));
    }
}
