import busqueda.Agente;
import busqueda.Ambiente;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class Main {

    public static void main(String[] args) {
        Agente agente = new Agente();
        Ambiente ambiente = new Ambiente();
        SearchBasedAgentSimulator simulador = new SearchBasedAgentSimulator(ambiente, agente);
        simulador.start();
    }
}
