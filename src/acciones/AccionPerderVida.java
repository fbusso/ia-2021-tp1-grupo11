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

public abstract class AccionPerderVida extends SearchAction {

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

        if (movimientoSiguiente.getLoboEnCamino()) {

            costo = Posicion.distanciaEntre(estadoAgente.getPosicion(),
                    estadoAgente.getEscenario().getPosicionInicialCaperucita());

            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioReiniciado(estadoAgente.getEscenario(), true);

            // Actualización del estado del agente.
            estadoAgente.setEscenario(nuevoEscenario);
            estadoAgente.setPosicion(nuevoEscenario.getPosicionInicialCaperucita());
            estadoAgente.setVidas(estadoAgente.getVidas() - 1);
            estadoAgente.setCantidadActualDulces(0);

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

        if (movimientoSiguiente.getLoboEnCamino()) {

            // Cálculo del nuevo escenario.
            Escenario nuevoEscenario = Escenario.obtenerEscenarioReiniciado(estadoAgente.getEscenario(), false);

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

    /**
     * Representa el costo de realizar un movimiento y perder una vida.
     * Se calcula como la distancia entre el punto origen y la posición inicial de caperucita, multiplicado por un
     * coeficiente de penalización (10).
     *
     * @return costo de realizar la acción.
     */
    @Override
    public Double getCost() {
        return 10 * costo;
    }
}
