package busqueda;

import auxiliar.AuxiliarIrArriba;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class Percepcion extends Perception {

    private char[][] matriz;


    private Boolean puedeAvanzarArriba;

    public Percepcion() {
    }

    public Percepcion(Agent agente, Environment ambiente) {
        super(agente, ambiente);
    }

    public void initPerception(Agent agent, Environment environment) {
        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) environment.getEnvironmentState();
        AuxiliarIrArriba auxiliarIrArriba = new AuxiliarIrArriba(estadoAmbiente.getEscenario().getMatriz(), estadoAmbiente.getPosicionCaperucita());
        puedeAvanzarArriba = !auxiliarIrArriba.getLoboEnCamino();
    }
}
