package acciones;

/**
 * Si el lobo no está abajo de Caperucita, ella se mueve en línea recta hacia abajo hasta encontrarse con el próximo
 * obstáculo (árbol), recolectando todos los dulces en el camino.
 */
public class IrAbajo extends AccionMovimiento {

    public IrAbajo() {
        avanzar = posicion -> posicion.i++;
        retroceder = posicion -> posicion.i--;
        evaluarPosicion = (posicion, matriz) -> posicion.i < matriz.length && matriz[posicion.i][posicion.j] != 'A';
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irAbajo";
    }
}

