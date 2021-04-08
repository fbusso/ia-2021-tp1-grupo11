package busqueda;

import acciones.IrArriba;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agente extends SearchBasedAgent {

    public Agente() {

        // Objetivo del agente.
        ObjetivoAgente objetivoAgente = new ObjetivoAgente();

        // Estado del agente.
        EstadoCaperucita estadoAgente = new EstadoCaperucita();
        this.setAgentState(estadoAgente);

        // TODO: Agregar el resto
        // Operadores.
        Vector<SearchAction> operadores = new Vector<SearchAction>();
        operadores.addElement(new IrArriba());

        Problem problema = new Problem(objetivoAgente, estadoAgente, operadores);
        this.setProblem(problema);
    }

    @Override
    public Action selectAction() {
        // Create the search strategy
        DepthFirstSearch estrategia = new DepthFirstSearch();
        Search busqueda = new Search(estrategia);

        // Genera un archivo XML del árbol de búsqueda.
        busqueda.setVisibleTree(Search.EFAIA_TREE);

        this.setSolver(busqueda);

        Action accionSeleccionada = null;
        try {
            accionSeleccionada = this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return accionSeleccionada;
    }

    /**
     * El simulador envía una percepción al agente para que actualice su estado.
     */
    @Override
    public void see(Perception percepcion) {
        this.getAgentState().updateState(percepcion);
    }
}
