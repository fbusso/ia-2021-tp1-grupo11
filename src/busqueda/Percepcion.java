package busqueda;

import dominio.Escenario;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.util.Arrays;

public class Percepcion extends Perception {

    private Escenario escenario;

    @Override
    public void initPerception(Agent agent, Environment environment) {
        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) environment.getEnvironmentState();
        escenario = estadoAmbiente.getEscenario();
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    @Override
    public String toString() {
        StringBuilder stringPercepcion = new StringBuilder("\n");
        for (char[] linea : escenario.getMatriz()) {
            stringPercepcion.append((Arrays.toString(linea))).append('\n');
        }

        return stringPercepcion.toString();
    }
}
