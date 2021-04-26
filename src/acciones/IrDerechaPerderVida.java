package acciones;

/**
 * Si el lobo está a la derecha de Caperucita, ella regresa a la posición inicial perdiendo una vida.
 * Los dulces recolectados vuelven a sus posiciones originales.
 */
public class IrDerechaPerderVida extends AccionPerderVida {

    public IrDerechaPerderVida() {
        avanzar = posicion -> posicion.j++;
        retroceder = posicion -> posicion.j--;
        evaluarPosicion = (posicion, matriz) -> posicion.j <= matriz[0].length && matriz[posicion.i][posicion.j] != 'A';
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irDerechaPerderVida";
    }
}
