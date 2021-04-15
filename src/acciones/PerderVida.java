package acciones;

import auxiliar.AuxiliarMovimiento;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.util.Collections;

public abstract class PerderVida extends SearchAction {

    protected SearchBasedAgentState obtenerEstadoActualizado(AuxiliarMovimiento auxiliar, EstadoCaperucita estadoAgente) {
        if (estadoAgente.getPosicion().equals(estadoAgente.getEscenario().getPosicionActualLobo()) || auxiliar.getLoboEnCamino()) {
            estadoAgente.setPosicion(estadoAgente.getEscenario().getPosicionInicialCaperucita());
            estadoAgente.setVidas(estadoAgente.getVidas() - 1);
            return estadoAgente;
            // No se debería modificar el escenario
            // estado.getEscenario().setMatriz(Escenario.removerDulces(matriz, auxiliar.getPosicionesDulces()));
        } else {
            return null;
        }
    }

    protected EnvironmentState obtenerEstadoAcualizado(AuxiliarMovimiento auxiliar, EstadoAmbiente estadoAmbiente, EstadoCaperucita estadoAgente) {
        if (estadoAgente.getPosicion().equals(estadoAgente.getEscenario().getPosicionActualLobo()) || auxiliar.getLoboEnCamino()) {
            // Cálculo del nuevo escenario
            estadoAgente.setPosicion(estadoAgente.getEscenario().getPosicionInicialCaperucita());
            estadoAgente.setVidas(estadoAgente.getVidas() - 1);

            Escenario nuevoEscenario = Escenario.obtenerEscenarioActualizado(
                    estadoAgente.getEscenario(),
                    estadoAgente.getPosicion(),
                    Collections.<Posicion>emptyList());

            // Actualización del estado del ambiente
            estadoAmbiente.setEscenario(nuevoEscenario);
            estadoAmbiente.setPosicionLobo(nuevoEscenario.getPosicionActualLobo());
            estadoAmbiente.setCantidadActualVidas(estadoAgente.getVidas() - 1);
            estadoAmbiente.setPosicionCaperucita(estadoAgente.getEscenario().getPosicionInicialCaperucita());
            return estadoAmbiente;
        } else {
            return null;
        }
    }
}
