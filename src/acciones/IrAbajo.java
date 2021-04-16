package acciones;

import auxiliar.AuxiliarIrAbajo;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

/**
 * Si el lobo no está abajo de Caperucita, ella se mueve en línea recta hacia abajo hasta encontrarse con el próximo
 * obstáculo (árbol), recolectando todos los dulces en el camino.
 */
public class IrAbajo extends Movimiento {

    /**
     * Actualiza un nodo del árbol de búsqueda mientras se ejecuta el algoritmo.
     * No actualiza el estado del mundo real.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estadoAgente = (EstadoCaperucita) s;
        char[][] matriz = estadoAgente.getEscenario().getMatriz();
        Posicion posicionActual = estadoAgente.getPosicion();
        AuxiliarIrAbajo auxiliar = new AuxiliarIrAbajo(matriz, posicionActual);
        return obtenerEstadoActualizado(auxiliar, estadoAgente);
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
        AuxiliarIrAbajo auxiliar = new AuxiliarIrAbajo(matriz, posicionActual);
        return obtenerEstadoAcualizado(auxiliar, estadoAmbiente, estadoAgente);
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
        return "irAbajo";
    }
}

