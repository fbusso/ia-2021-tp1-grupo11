package acciones;

import auxiliar.Avanzar;
import auxiliar.EvaluarPosicion;
import auxiliar.Movimiento;
import auxiliar.Retroceder;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

/**
 * Si el lobo no está a la izquierda de Caperucita, ella se mueve en línea recta hacia la izquierda hasta encontrarse
 * con el próximo obstáculo (árbol), recolectando todos los dulces en el camino.
 */
public class IrIzquierda extends AccionMovimiento {

    private final Retroceder retroceder;
    private final EvaluarPosicion evaluarPosicion;
    private final Avanzar avanzar;

    public IrIzquierda() {
        this.avanzar = (posicion -> posicion.j--);
        this.retroceder = (posicion -> posicion.j++);
        this.evaluarPosicion = (posicion, matriz) -> posicion.j >= 0 && matriz[posicion.i][posicion.j] != 'A';
    }

    /**
     * Actualiza un nodo del árbol de búsqueda mientras se ejecuta el algoritmo.
     * No actualiza el estado del mundo real.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estadoAgente = (EstadoCaperucita) s;

        char[][] matriz = estadoAgente.getEscenario().getMatriz();
        Posicion posicionActual = estadoAgente.getPosicion();
        Movimiento movimientoSiguiente = new Movimiento(posicionActual, matriz, avanzar, retroceder, evaluarPosicion);
        return obtenerEstadoActualizado(movimientoSiguiente, estadoAgente);
    }

    /**
     * Actualiza el estado del agente y del mundo real.
     */
    @Override
    public EnvironmentState execute(AgentState agentState, EnvironmentState environmentState) {
        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) environmentState;
        EstadoCaperucita estadoAgente = (EstadoCaperucita) agentState;

        char[][] matriz = estadoAgente.getEscenario().getMatriz();
        Posicion posicionActual = estadoAgente.getPosicion();
        Movimiento movimientoSiguiente = new Movimiento(posicionActual, matriz, avanzar, retroceder, evaluarPosicion);
        return obtenerEstadoAcualizado(movimientoSiguiente, estadoAmbiente, estadoAgente);
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irIzquierda";
    }
}
