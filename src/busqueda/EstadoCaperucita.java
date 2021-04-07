package busqueda;

import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoCaperucita extends SearchBasedAgentState {

    private Posicion posicion;
    private Integer vidas;
    private Integer cantidadDulces;
    private Escenario escenario;

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Integer getVidas() {
        return vidas;
    }

    public void setVidas(Integer vidas) {
        this.vidas = vidas;
    }

    public Integer getCantidadDulces() {
        return cantidadDulces;
    }

    public void setCantidadDulces(Integer cantidadDulces) {
        this.cantidadDulces = cantidadDulces;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    //TODO revisar este meotodo
    @Override
    public SearchBasedAgentState clone() {
        EstadoCaperucita nuevoEstado = new EstadoCaperucita();

        nuevoEstado.setVidas(this.vidas);
        nuevoEstado.setCantidadDulces(this.cantidadDulces);
        nuevoEstado.setPosicion(this.posicion);

        return nuevoEstado;
    }

    @Override
    public void updateState(Perception p) {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initState() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean equals(Object obj) {
        return ((EstadoCaperucita) obj).getVidas().equals(this.getVidas())
                && ((EstadoCaperucita) obj).getPosicion().equals(this.getPosicion())
                && ((EstadoCaperucita) obj).getCantidadDulces().equals(this.getCantidadDulces());
    }

}
