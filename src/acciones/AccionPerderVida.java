package acciones;

import auxiliar.Movimiento;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public abstract class AccionPerderVida extends SearchAction {

    protected SearchBasedAgentState obtenerEstadoActualizado(Movimiento auxiliar, EstadoCaperucita estadoAgente) {
        if (estadoAgente.getPosicion().equals(estadoAgente.getEscenario().getPosicionActualLobo()) || auxiliar.getLoboEnCamino()) {
            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioReiniciado(estadoAgente.getEscenario(), false);

            // Actualización del estado del agente.
            estadoAgente.setEscenario(nuevoEscenario);
            estadoAgente.setPosicion(nuevoEscenario.getPosicionActualCaperucita());
            estadoAgente.setVidas(estadoAgente.getVidas() - 1);
            estadoAgente.setCantidadActualDulces(0);
            return estadoAgente;
        } else {
            return null;
        }
    }

    protected EnvironmentState obtenerEstadoAcualizado(Movimiento auxiliar, EstadoAmbiente estadoAmbiente, EstadoCaperucita estadoAgente) {
        if (estadoAgente.getPosicion().equals(estadoAgente.getEscenario().getPosicionActualLobo()) || auxiliar.getLoboEnCamino()) {
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
        } else {
            return null;
        }
    }
}
