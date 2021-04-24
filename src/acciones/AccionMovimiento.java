package acciones;

import auxiliar.Movimiento;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public abstract class AccionMovimiento extends SearchAction implements ActualizarEstado {

    private Double costo;

    @Override
    public SearchBasedAgentState obtenerEstadoActualizado(Movimiento movimientoSiguiente, EstadoCaperucita estadoAgente) {
        if (!movimientoSiguiente.getLoboEnCamino()) {

            costo = Posicion.distanciaEntre(movimientoSiguiente.getPosicionFinal(), estadoAgente.getPosicion());
//            estadoAgente.actualizarPosicionesVisitadas(estadoAgente.getPosicion());

            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioActualizado(
                    estadoAgente.getEscenario(),
                    movimientoSiguiente.getPosicionFinal(),
                    movimientoSiguiente.getPosicionesDulces(),
                    true);

            Integer nuevaCantidadDulces = estadoAgente.getCantidadActualDulces() + movimientoSiguiente.getCantidadDulcesEnCamino();

            // Actualización del estado del agente.
            estadoAgente.setPosicion(movimientoSiguiente.getPosicionFinal());
            estadoAgente.setCantidadActualDulces(nuevaCantidadDulces);
            estadoAgente.setEscenario(nuevoEscenario);

            return estadoAgente;
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
                    movimientoSiguiente.getPosicionesDulces(),
                    false);

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
        return costo;
    }
}
