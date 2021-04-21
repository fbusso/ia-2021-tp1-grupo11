package acciones;

import auxiliar.Movimiento;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public abstract class AccionMovimiento extends SearchAction {

    protected SearchBasedAgentState obtenerEstadoActualizado(Movimiento auxiliar, EstadoCaperucita estado) {
        if (!estado.getPosicion().equals(estado.getEscenario().getPosicionActualLobo())
                && !estado.getPosicion().equals(auxiliar.getPosicionFinal())
                && !auxiliar.getLoboEnCamino()) {

            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioActualizado(
                    estado.getEscenario(),
                    auxiliar.getPosicionFinal(),
                    auxiliar.getPosicionesDulces());

            Integer nuevaCantidadDulces = estado.getCantidadActualDulces() + auxiliar.getCantidadDulcesEnCamino();

            // Actualización del estado del agente.
            estado.setPosicion(auxiliar.getPosicionFinal());
            estado.setCantidadActualDulces(nuevaCantidadDulces);
            estado.setEscenario(nuevoEscenario);

            return estado;
        } else {
            return null;
        }
    }

    protected EnvironmentState obtenerEstadoAcualizado(Movimiento auxiliar, EstadoAmbiente estadoAmbiente, EstadoCaperucita estadoAgente) {
        if (!estadoAgente.getPosicion().equals(estadoAgente.getEscenario().getPosicionActualLobo())
                && !estadoAgente.getPosicion().equals(auxiliar.getPosicionFinal())
                && !auxiliar.getLoboEnCamino()) {

            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioActualizado(
                    estadoAgente.getEscenario(),
                    auxiliar.getPosicionFinal(),
                    auxiliar.getPosicionesDulces());

            Integer nuevaCantidadDulces = estadoAgente.getCantidadActualDulces() + auxiliar.getCantidadDulcesEnCamino();

            // Actualización del estado del agente.
            estadoAgente.setEscenario(nuevoEscenario);
            estadoAgente.setPosicion(nuevoEscenario.getPosicionActualCaperucita());
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