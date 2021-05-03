package busqueda;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class FuncionCosto implements IStepCostFunction {

    @Override
    public double calculateCost(NTree nodo) {
        return nodo.getAction().getCost();
    }
}
