package busqueda;

import dominio.Escenario;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoAgente extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        EstadoCaperucita estado = (EstadoCaperucita) agentState;
        return estado.getVidas() > 0 &&
                Escenario.esCampoDeFlores(estado.getEscenario(), estado.getPosicion()) &&
                estado.getCantidadActualDulces().equals(estado.getCantidadTotalDulces());
    }

}
