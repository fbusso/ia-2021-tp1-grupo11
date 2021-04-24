package acciones;

/**
 * Si el lobo no está arriba de Caperucita, ella se mueve en línea recta hacia arriba hasta encontrarse con el próximo
 * obstáculo (árbol), recolectando todos los dulces en el camino.
 */
public class IrArriba extends AccionMovimiento {

    public IrArriba() {
        this.avanzar = (posicion -> posicion.i--);
        this.retroceder = (posicion -> posicion.i++);
        this.evaluarPosicion = (posicion, matriz) -> posicion.i >= 0 && matriz[posicion.i][posicion.j] != 'A';
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irArriba";
    }
}
