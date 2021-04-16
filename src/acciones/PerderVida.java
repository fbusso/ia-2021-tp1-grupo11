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
            Integer nuevaCantidadDulces = estadoAgente.getCantidadActualDulces() + auxiliar.getCantidadDulcesEnCamino();
            estadoAgente.setPosicion(auxiliar.getPosicionFinal());
            estadoAgente.setVidas(estadoAgente.getVidas() - 1);
            estadoAgente.setCantidadActualDulces(nuevaCantidadDulces);
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
            Escenario nuevoEscenario = Escenario.obtenerEscenarioActualizado(
                    estadoAgente.getEscenario(),
                    auxiliar.getPosicionFinal(),
                    Collections.<Posicion>emptyList());

            Integer nuevaCantidadDulces = estadoAgente.getCantidadActualDulces() + auxiliar.getCantidadDulcesEnCamino();

            estadoAgente.setEscenario(nuevoEscenario);
            estadoAgente.setPosicion(auxiliar.getPosicionFinal());
            estadoAgente.setVidas(estadoAgente.getVidas() - 1);
            estadoAgente.setCantidadActualDulces(nuevaCantidadDulces);

            // Actualización del estado del ambiente
            estadoAmbiente.setEscenario(nuevoEscenario);
            estadoAmbiente.setCantidadActualVidas(estadoAgente.getVidas() - 1);
            estadoAmbiente.setPosicionLobo(nuevoEscenario.getPosicionActualLobo());
            estadoAmbiente.setCantidadDulcesRecolectados(nuevaCantidadDulces);
            estadoAmbiente.setPosicionCaperucita(nuevoEscenario.getPosicionActualCaperucita());
            return estadoAmbiente;
        } else {
            return null;
        }
    }
}
