package acciones;

import auxiliar.Movimiento;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public abstract class AccionPerderVida extends SearchAction implements ActualizarEstado {

    @Override
    public SearchBasedAgentState obtenerEstadoActualizado(Movimiento movimiento, EstadoCaperucita estadoAgente) {
        if (movimiento.getLoboEnCamino()) {
            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioReiniciado(estadoAgente.getEscenario(), false);

            // Actualización del estado del agente.
            estadoAgente.setEscenario(nuevoEscenario);
            estadoAgente.setPosicion(nuevoEscenario.getPosicionInicialCaperucita());
            estadoAgente.setVidas(estadoAgente.getVidas() - 1);
            estadoAgente.setCantidadActualDulces(0);
            return estadoAgente;
        }

        return null;
    }

    @Override
    public EnvironmentState obtenerEstadoAcualizado(Movimiento movimiento, EstadoAmbiente estadoAmbiente, EstadoCaperucita estadoAgente) {
        if (movimiento.getLoboEnCamino()) {
            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioReiniciado(estadoAgente.getEscenario(), true);

            // Actualización del estado del agente.
            estadoAgente.setEscenario(nuevoEscenario);
            estadoAgente.setPosicion(nuevoEscenario.getPosicionActualCaperucita());
            estadoAgente.setVidas(estadoAgente.getVidas() - 1);
            estadoAgente.setCantidadActualDulces(0);

            // Actualización del estado del ambiente.
            estadoAmbiente.setEscenario(nuevoEscenario);
            estadoAmbiente.setPosicionLobo(nuevoEscenario.getPosicionActualLobo());
            estadoAmbiente.setPosicionCaperucita(nuevoEscenario.getPosicionActualCaperucita());
            estadoAmbiente.setCantidadActualVidas(estadoAgente.getVidas() - 1);
            estadoAmbiente.setCantidadDulcesRecolectados(0);
            return estadoAmbiente;
        }

        return null;
    }

    @Override
    public Double getCost() {
        return 10.00;
    }
}
