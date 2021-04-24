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
 * Si el lobo está arriba de Caperucita, ella regresa a la posición inicial perdiendo una vida.
 * Los dulces recolectados vuelven a sus posiciones originales.
 */
public class IrArribaPerderVida extends AccionPerderVida {

    private final Retroceder retroceder;
    private final EvaluarPosicion evaluarPosicion;
    private final Avanzar avanzar;

    public IrArribaPerderVida() {
        this.avanzar = (posicion -> posicion.i--);
        this.retroceder = (posicion -> posicion.i++);
        this.evaluarPosicion = (posicion, matriz) -> posicion.i >= 0 && matriz[posicion.i][posicion.j] != 'A';
    }

    /**
     * Actualiza un nodo del árbol de búsqueda mientras se ejecuta el algoritmo.
     * No actualiza el estado del mundo real.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estado = (EstadoCaperucita) s;

        char[][] matriz = estado.getEscenario().getMatriz();
        Posicion posicionActual = estado.getPosicion();
        Movimiento movimientoSiguiente = new Movimiento(posicionActual, matriz, avanzar, retroceder, evaluarPosicion);
        return obtenerEstadoActualizado(movimientoSiguiente, estado);
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
        Movimiento auxiliar = new Movimiento(posicionActual, matriz, avanzar, retroceder, evaluarPosicion);
        return obtenerEstadoAcualizado(auxiliar, estadoAmbiente, estadoAgente);
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irArribaPerderVida";
    }
}
