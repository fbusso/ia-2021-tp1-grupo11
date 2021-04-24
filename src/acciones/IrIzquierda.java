package acciones;

/**
 * Si el lobo no está a la izquierda de Caperucita, ella se mueve en línea recta hacia la izquierda hasta encontrarse
 * con el próximo obstáculo (árbol), recolectando todos los dulces en el camino.
 */
public class IrIzquierda extends AccionMovimiento {

    public IrIzquierda() {
        this.avanzar = (posicion -> posicion.j--);
        this.retroceder = (posicion -> posicion.j++);
        this.evaluarPosicion = (posicion, matriz) -> posicion.j >= 0 && matriz[posicion.i][posicion.j] != 'A';
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irIzquierda";
    }
}
