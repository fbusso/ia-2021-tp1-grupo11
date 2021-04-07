package busqueda;

import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

    private Escenario escenario;
    private Posicion posicionCaperucita;
    private Integer cantidadDulcesRecolectados;

    public EstadoAmbiente() {
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setPosicionCaperucita(Posicion posicionCaperucita) {
        this.posicionCaperucita = posicionCaperucita;
    }

    public Integer getCantidadDulcesRecolectados() {
        return cantidadDulcesRecolectados;
    }

    public void setCantidadDulcesRecolectados(Integer cantidadDulcesRecolectados) {
        this.cantidadDulcesRecolectados = cantidadDulcesRecolectados;
    }

    @Override
    public void initState() {
        this.escenario = new Escenario(9, 14);
        this.posicionCaperucita = new Posicion(0, 0);
        this.cantidadDulcesRecolectados = 0;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
}
