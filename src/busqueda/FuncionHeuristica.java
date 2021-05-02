package busqueda;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class FuncionHeuristica implements IEstimatedCostFunction {

    @Override
    public double getEstimatedCost(NTree nodo) {
        EstadoCaperucita estadoAgente = (EstadoCaperucita) nodo.getAgentState();
        return (double) 1 / estadoAgente.getVidas();
    }
}
