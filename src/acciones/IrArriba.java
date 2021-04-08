package acciones;

import auxiliar.AuxiliarIrArriba;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrArriba extends SearchAction {

    /**
     * Actualiza un nodo del árbol de búsqueda mientras se ejecuta el algoritmo.
     * No actualiza el estado del mundo real.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estado = (EstadoCaperucita) s;
        char[][] matriz = estado.getEscenario().getMatriz();
        Posicion posicionActual = estado.getPosicion();
        AuxiliarIrArriba auxiliar = new AuxiliarIrArriba(matriz, posicionActual);

        if (!auxiliar.getLoboEnCamino()) {
            Integer nuevaCantidadDulces = estado.getCantidadActualDulces() + auxiliar.getCantidadDulcesEnCamino();
            estado.setPosicion(auxiliar.getPosicionFinal());
            estado.setCantidadActualDulces(nuevaCantidadDulces);

            // TODO: Revisar por que esto rompe todo
            // estado.getEscenario().setMatriz(Escenario.removerDulces(matriz, auxiliar.getPosicionesDulces()));
            return estado;
        }

        return null;
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
        AuxiliarIrArriba auxiliar = new AuxiliarIrArriba(matriz, posicionActual);

        if (!auxiliar.getLoboEnCamino()) {
            Integer cantidadDulcesActualizada = estadoAgente.getCantidadActualDulces() + auxiliar.getCantidadDulcesEnCamino();
            char[][] matrizActualizada = Escenario.removerDulces(matriz, auxiliar.getPosicionesDulces());

            // Actualización del estado del agente
            estadoAgente.setPosicion(auxiliar.getPosicionFinal());
            estadoAgente.setCantidadActualDulces(cantidadDulcesActualizada);
            estadoAgente.getEscenario().setMatriz(matrizActualizada);

            // Actualización del estado del ambiente
            estadoAmbiente.setPosicionCaperucita(auxiliar.getPosicionFinal());
            estadoAmbiente.setCantidadDulcesRecolectados(cantidadDulcesActualizada);
            estadoAmbiente.getEscenario().setMatriz(matrizActualizada);
            return estadoAmbiente;
        }

        return null;
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
        return "irArriba";
    }
}
