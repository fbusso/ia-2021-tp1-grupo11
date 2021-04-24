package acciones;

import auxiliar.Movimiento;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public abstract class AccionMovimiento extends SearchAction implements ActualizarEstado {

    @Override
    public SearchBasedAgentState obtenerEstadoActualizado(Movimiento movimientoSiguiente, EstadoCaperucita estado) {
        if (!movimientoSiguiente.getLoboEnCamino()) {

            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioActualizado(
                    estado.getEscenario(),
                    movimientoSiguiente.getPosicionFinal(),
                    movimientoSiguiente.getPosicionesDulces());

            Integer nuevaCantidadDulces = estado.getCantidadActualDulces() + movimientoSiguiente.getCantidadDulcesEnCamino();

            // Actualización del estado del agente.
            estado.setPosicion(movimientoSiguiente.getPosicionFinal());
            estado.setCantidadActualDulces(nuevaCantidadDulces);
            estado.setEscenario(nuevoEscenario);

            return estado;
        }

        return null;
    }

    @Override
    public EnvironmentState obtenerEstadoAcualizado(Movimiento movimientoSiguiente, EstadoAmbiente estadoAmbiente, EstadoCaperucita estadoAgente) {
        if (!movimientoSiguiente.getLoboEnCamino()) {

            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioActualizado(
                    estadoAgente.getEscenario(),
                    movimientoSiguiente.getPosicionFinal(),
                    movimientoSiguiente.getPosicionesDulces());

            Integer nuevaCantidadDulces = estadoAgente.getCantidadActualDulces() + movimientoSiguiente.getCantidadDulcesEnCamino();

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
        }

        return null;
    }

    @Override
    public Double getCost() {
        return 1.00;
    }
}
