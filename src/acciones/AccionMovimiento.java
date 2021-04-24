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

public abstract class AccionMovimiento extends SearchAction implements ActualizarEstado {

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
        return obtenerEstadoActualizado(movimientoSiguiente, estadoAgente);
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
        return obtenerEstadoAcualizado(movimientoSiguiente, estadoAmbiente, estadoAgente);
    }

    @Override
    public SearchBasedAgentState obtenerEstadoActualizado(Movimiento movimientoSiguiente, EstadoCaperucita estadoAgente) {
        if (!movimientoSiguiente.getLoboEnCamino()) {

            costo = Posicion.distanciaEntre(movimientoSiguiente.getPosicionFinal(), estadoAgente.getPosicion());

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
