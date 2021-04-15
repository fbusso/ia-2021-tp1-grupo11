package busqueda;

import dominio.Escenario;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class Ambiente extends Environment {

    public Ambiente(Escenario escenario) {
        this.environmentState = new EstadoAmbiente(escenario);
    }

    public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }

    @Override
    public Perception getPercept() {
        Percepcion percepcion = new Percepcion();
        EstadoAmbiente estadoAmbiente = this.getEnvironmentState();
        percepcion.setEscenario(estadoAmbiente.getEscenario());
        return percepcion;
    }

    // FIXME
    @Override
    public boolean agentFailed(Action actionReturned) {
/*        EstadoAmbiente estadoAmbiente = this.getEnvironmentState();
        return estadoAmbiente.getCantidadActualVidas() == 0;*/
        return false;
    }
}
