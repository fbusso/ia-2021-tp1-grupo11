package acciones;

import auxiliar.Avanzar;
import auxiliar.EvaluarPosicion;
import auxiliar.Movimiento;
import auxiliar.Retroceder;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public abstract class AccionMovimiento extends SearchAction {

    protected Retroceder retroceder;
    protected EvaluarPosicion evaluarPosicion;
    protected Avanzar avanzar;
    private Double costo;

    /**
     * Actualiza un nodo del árbol de búsqueda mientras se ejecuta el algoritmo.
     * No actualiza el estado del mundo real.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estadoAgente = (EstadoCaperucita) s;
        char[][] matriz = estadoAgente.getEscenario().getMatriz();
        Posicion posicionActual = estadoAgente.getPosicion();
        Movimiento movimientoSiguiente = new Movimiento(posicionActual, matriz, avanzar, retroceder, evaluarPosicion);

        if (!movimientoSiguiente.getLoboEnCamino() && !movimientoSiguiente.getPosicionFinal().equals(estadoAgente.getPosicion())) {

            costo = (double) (estadoAgente.getEscenario().getCantidadDulces() - estadoAgente.getCantidadActualDulces());

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

    /**
     * Actualiza el estado del agente y del mundo real.
     */
    @Override
    public EnvironmentState execute(AgentState agentState, EnvironmentState environmentState) {
        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) environmentState;
        EstadoCaperucita estadoAgente = (EstadoCaperucita) agentState;

        char[][] matriz = estadoAgente.getEscenario().getMatriz();
        Posicion posicionActual = estadoAgente.getPosicion();
        Movimiento movimientoSiguiente = new Movimiento(posicionActual, matriz, avanzar, retroceder, evaluarPosicion);

        if (!movimientoSiguiente.getLoboEnCamino() && !movimientoSiguiente.getPosicionFinal().equals(estadoAgente.getPosicion())) {

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

    /**
     * Representa el costo de realizar un movimiento sin perder una vida.
     * Se calcula como la distancia entre el punto origen y el punto destino.
     *
     * @return costo de realizar la acción.
     */
    @Override
    public Double getCost() {
        return costo;
    }
}
