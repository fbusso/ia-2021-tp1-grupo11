package acciones;

/**
 * Si el lobo está arriba de Caperucita, ella regresa a la posición inicial perdiendo una vida.
 * Los dulces recolectados vuelven a sus posiciones originales.
 */
public class IrArribaPerderVida extends AccionPerderVida {

    public IrArribaPerderVida() {
        avanzar = posicion -> posicion.i--;
        retroceder = posicion -> posicion.i++;
        evaluarPosicion = (posicion, matriz) -> posicion.i >= 0 && matriz[posicion.i][posicion.j] != 'A';
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irArribaPerderVida";
    }
}
