package acciones;

/**
 * Si el lobo no está a la derecha de Caperucita, ella se mueve en línea recta hacia la derecha hasta encontrarse con
 * el próximo obstáculo (árbol), recolectando todos los dulces en el camino.
 */
public class IrDerecha extends AccionMovimiento {

    public IrDerecha() {
        this.avanzar = (posicion -> posicion.j++);
        this.retroceder = (posicion -> posicion.j--);
        this.evaluarPosicion = (posicion, matriz) -> posicion.j <= matriz[0].length && matriz[posicion.i][posicion.j] != 'A';
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irDerecha";
    }
}
