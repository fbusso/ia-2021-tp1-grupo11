import auxiliar.AuxiliarEscenario;
import busqueda.Agente;
import busqueda.Ambiente;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class Main {

    public static void main(String[] args) {
        char[][] escenario = AuxiliarEscenario.obtenerMatriz();




        Agente agente = new Agente(escenario);
        Ambiente ambiente = new Ambiente(escenario);
        SearchBasedAgentSimulator simulador = new SearchBasedAgentSimulator(ambiente, agente);
        simulador.start();
    }
}
