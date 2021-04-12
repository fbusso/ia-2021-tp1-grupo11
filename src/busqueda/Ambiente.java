package busqueda;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class Ambiente extends Environment {

    public Ambiente(char[][] escenario) {
        this.environmentState = new EstadoAmbiente(escenario);
    }

    public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }

    @Override
    public Perception getPercept() {
        Percepcion percepcion = new Percepcion();
        percepcion.setMatriz(this.getEnvironmentState().getEscenario().getMatriz());
        percepcion.setCantidadAcualDulces(this.getEnvironmentState().getCantidadDulcesRecolectados());
        percepcion.setPosicionActual(this.getEnvironmentState().getPosicionCaperucita());
        return percepcion;
    }

    @Override
    public boolean agentFailed(Action actionReturned) {
        return false;
    }
}
