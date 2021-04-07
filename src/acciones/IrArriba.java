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

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estado = (EstadoCaperucita) s;
        char[][] matriz = estado.getEscenario().getMatriz();
        Posicion posicionActual = estado.getPosicion();
        AuxiliarIrArriba auxiliar = new AuxiliarIrArriba(matriz, posicionActual);

        if (!auxiliar.getLoboEnCamino()) {
            Integer nuevaCantidadDulces = estado.getCantidadDulces() + auxiliar.getCantidadDulcesEnCamino();
            estado.setPosicion(auxiliar.getPosicionFinal());
            estado.setCantidadDulces(nuevaCantidadDulces);
            estado.getEscenario().setMatriz(Escenario.removerDulces(matriz, auxiliar.getPosicionesDulces()));
        }

        return null;
    }

    @Override
    public EnvironmentState execute(AgentState agentState, EnvironmentState environmentState) {
        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) environmentState;
        EstadoCaperucita estadoAgente = (EstadoCaperucita) agentState;

        char[][] matriz = estadoAgente.getEscenario().getMatriz();
        Posicion posicionActual = estadoAgente.getPosicion();
        AuxiliarIrArriba auxiliar = new AuxiliarIrArriba(matriz, posicionActual);

        if (!auxiliar.getLoboEnCamino()) {
            Integer cantidadDulcesActualizada = estadoAgente.getCantidadDulces() + auxiliar.getCantidadDulcesEnCamino();
            char[][] matrizActualizada = Escenario.removerDulces(matriz, auxiliar.getPosicionesDulces());

            // Actualizacion del estado del agente
            estadoAgente.setPosicion(auxiliar.getPosicionFinal());
            estadoAgente.setCantidadDulces(cantidadDulcesActualizada);
            estadoAgente.getEscenario().setMatriz(matrizActualizada);

            // Actualizacion del estado del ambiente
            estadoAmbiente.setPosicionCaperucita(auxiliar.getPosicionFinal());
            estadoAmbiente.setCantidadDulcesRecolectados(cantidadDulcesActualizada);
            estadoAmbiente.getEscenario().setMatriz(matrizActualizada);
            return estadoAmbiente;
        }

        return null;
    }

    @Override
    public Double getCost() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
