package busqueda;

import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

    private Escenario escenario;
    private Posicion posicionCaperucita;
    private Integer cantidadTotalDulces;
    private Integer cantidadDulcesRecolectados;

    public EstadoAmbiente() {
        this.initState();
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    public Posicion getPosicionCaperucita() {
        return posicionCaperucita;
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

    public Integer getCantidadTotalDulces() {
        return cantidadTotalDulces;
    }

    public void setCantidadTotalDulces(Integer cantidadTotalDulces) {
        this.cantidadTotalDulces = cantidadTotalDulces;
    }

    @Override
    public void initState() {
        this.escenario = new Escenario();
        this.posicionCaperucita = escenario.obtenerPosicionInicial();
        this.cantidadTotalDulces = escenario.getCantidadDulces();
        this.cantidadDulcesRecolectados = 0;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
}
