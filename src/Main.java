import auxiliar.AuxiliarEscenario;
import busqueda.Agente;
import busqueda.Ambiente;
import dominio.Escenario;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class Main {

    public static void main(String[] args) {
        // Obtener el escenario inicial para inicializar el agente y el ambiente.
        char[][] matrizEscenario = AuxiliarEscenario.obtenerMatriz();


        Escenario escenario = new Escenario(matrizEscenario);

        Agente agente = new Agente(escenario);
        Ambiente ambiente = new Ambiente(escenario);
        SearchBasedAgentSimulator simulador = new SearchBasedAgentSimulator(ambiente, agente);
        simulador.start();
    }
}
