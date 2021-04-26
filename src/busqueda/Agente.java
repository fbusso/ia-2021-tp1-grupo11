package busqueda;

import acciones.*;
import dominio.Escenario;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.UniformCostSearch;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agente extends SearchBasedAgent {

    public Agente(Escenario escenario) {

        // Objetivo del agente.
        ObjetivoAgente objetivoAgente = new ObjetivoAgente();

        // Estado del agente.
        EstadoCaperucita estadoAgente = new EstadoCaperucita(escenario);
        this.setAgentState(estadoAgente);

        Vector<SearchAction> operadores = new Vector<>();

        operadores.addElement(new IrArriba());
        operadores.addElement(new IrDerecha());
        operadores.addElement(new IrAbajo());
        operadores.addElement(new IrIzquierda());

        operadores.addElement(new IrDerechaPerderVida());
        operadores.addElement(new IrArribaPerderVida());
        operadores.addElement(new IrAbajoPerderVida());
        operadores.addElement(new IrIzquierdaPerderVida());

        Problem problema = new Problem(objetivoAgente, estadoAgente, operadores);
        this.setProblem(problema);
    }

    @Override
    public Action selectAction() {

        //Búsqueda en Primero en Amplitud (Breathe First Search).
        BreathFirstSearch estrategia = new BreathFirstSearch();

        // Estrategia de costo uniforme.
        // UniformCostSearch estrategia = new UniformCostSearch(new FuncionCosto());

        // Búsqueda en Primero en Profundidad (Depth First Search).
        // DepthFirstSearch estrategia = new DepthFirstSearch();

        // Estrategia A Estrella.
        // AStarSearch estrategia = new AStarSearch(new FuncionCosto(), new FuncionHeuristica());

        Search busqueda = new Search(estrategia);
        busqueda.setVisibleTree(Search.PDF_TREE);
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
