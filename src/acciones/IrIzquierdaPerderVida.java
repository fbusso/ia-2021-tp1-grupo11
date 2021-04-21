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
 * Si el lobo está a la izquierda de Caperucita, ella regresa a la posición inicial perdiendo una vida.
 * Los dulces recolectados vuelven a sus posiciones originales.
 */
public class IrIzquierdaPerderVida extends AccionPerderVida {

    private final Retroceder retroceder;
    private final EvaluarPosicion evaluarPosicion;
    private final Avanzar avanzar;

    public IrIzquierdaPerderVida() {
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
        Movimiento movimientoSiguiente = new Movimiento(posicionActual, matriz, avanzar, retroceder, evaluarPosicion);
        return obtenerEstadoAcualizado(movimientoSiguiente, estadoAmbiente, estadoAgente);
    }

    @Override
    public Double getCost() {
        return 1.00;
    }

    /**
     * Este método no es importante para un agente basado en búsqueda.
     */
    @Override
    public String toString() {
        return "irIzquierdaPerderVida";
    }
}

