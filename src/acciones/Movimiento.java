package acciones;

import auxiliar.AuxiliarMovimiento;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public abstract class Movimiento extends SearchAction {

    protected SearchBasedAgentState obtenerEstadoActualizado(AuxiliarMovimiento auxiliar, EstadoCaperucita estado) {
        if (!estado.getPosicion().equals(estado.getEscenario().getPosicionActualLobo())
                && !estado.getPosicion().equals(auxiliar.getPosicionFinal())
                && !auxiliar.getLoboEnCamino()) {
            Integer nuevaCantidadDulces = estado.getCantidadActualDulces() + auxiliar.getCantidadDulcesEnCamino();
            estado.setPosicion(auxiliar.getPosicionFinal());
            estado.setCantidadActualDulces(nuevaCantidadDulces);

            // No se debería modificar el escenario
            // estado.getEscenario().setMatriz(Escenario.removerDulces(matriz, auxiliar.getPosicionesDulces()));
            return estado;
        } else {
            return null;
        }
    }

    protected EnvironmentState obtenerEstadoAcualizado(AuxiliarMovimiento auxiliar, EstadoAmbiente estadoAmbiente, EstadoCaperucita estadoAgente) {
        if (!estadoAgente.getPosicion().equals(estadoAgente.getEscenario().getPosicionActualLobo())
                && !estadoAgente.getPosicion().equals(auxiliar.getPosicionFinal())
                && !auxiliar.getLoboEnCamino()) {
            // Cálculo del nuevo escenario
            Escenario nuevoEscenario = Escenario.obtenerEscenarioActualizado(
                    estadoAgente.getEscenario(),
                    auxiliar.getPosicionFinal(),
                    auxiliar.getPosicionesDulces());

            Integer nuevaCantidadDulces = estadoAgente.getCantidadActualDulces() + auxiliar.getCantidadDulcesEnCamino();

            // Actualización del estado del agente
            estadoAgente.setEscenario(nuevoEscenario);
            estadoAgente.setPosicion(auxiliar.getPosicionFinal());
            estadoAgente.setCantidadActualDulces(nuevaCantidadDulces);

            // Actualización del estado del ambiente
            estadoAmbiente.setEscenario(nuevoEscenario);
            estadoAmbiente.setPosicionCaperucita(nuevoEscenario.getPosicionActualCaperucita());
            estadoAmbiente.setPosicionLobo(nuevoEscenario.getPosicionActualLobo());
            estadoAmbiente.setCantidadDulcesRecolectados(nuevaCantidadDulces);

            return estadoAmbiente;
        } else {
            return null;
        }
    }
}
