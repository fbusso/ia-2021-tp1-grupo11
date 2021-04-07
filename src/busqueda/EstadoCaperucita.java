package busqueda;

import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoCaperucita extends SearchBasedAgentState {

    private Posicion posicion;
    private Integer vidas;
    private Integer cantidadActualDulces;
    private Integer cantidadTotalDulces;
    private Escenario escenario;

    public EstadoCaperucita() {
        this.initState();
    }

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

    public Integer getCantidadActualDulces() {
        return cantidadActualDulces;
    }

    public void setCantidadActualDulces(Integer cantidadActualDulces) {
        this.cantidadActualDulces = cantidadActualDulces;
    }

    public Integer getCantidadTotalDulces() {
        return cantidadTotalDulces;
    }

    public void setCantidadTotalDulces(Integer cantidadTotalDulces) {
        this.cantidadTotalDulces = cantidadTotalDulces;
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
        nuevoEstado.setEscenario(this.escenario);
        nuevoEstado.setCantidadActualDulces(this.cantidadActualDulces);
        nuevoEstado.setCantidadTotalDulces(this.cantidadTotalDulces);
        nuevoEstado.setPosicion(this.posicion);

        return nuevoEstado;
    }

    @Override
    public void updateState(Perception p) {

        Percepcion percepcion = (Percepcion) p;


        // TODO Auto-generated method stub

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initState() {
        vidas = 3;
        cantidadActualDulces = 0;
        escenario = new Escenario();
        posicion = escenario.obtenerPosicionInicial();
        cantidadTotalDulces = escenario.getCantidadDulces();
    }

    @Override
    public boolean equals(Object obj) {
        return ((EstadoCaperucita) obj).getVidas().equals(this.getVidas())
                && ((EstadoCaperucita) obj).getPosicion().equals(this.getPosicion())
                && ((EstadoCaperucita) obj).getCantidadActualDulces().equals(this.getCantidadActualDulces());
    }
}
