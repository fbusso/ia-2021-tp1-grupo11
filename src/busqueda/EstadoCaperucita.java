package busqueda;

import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoCaperucita extends SearchBasedAgentState {

    private Integer cantidadActualDulces;
    private Integer cantidadTotalDulces;
    private Escenario escenario;
    private Posicion posicion;
    private Integer vidas;

    public EstadoCaperucita() {
    }

    public EstadoCaperucita(char[][] escenario) {
        this.escenario = new Escenario(escenario);
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
    public void initState() {
        this.vidas = 3;
        this.cantidadActualDulces = 0;
        this.posicion = escenario.getPosicionIncial();
        this.cantidadTotalDulces = escenario.getCantidadDulces();
    }

    @Override
    public void updateState(Perception p) {
        Percepcion percepcion = (Percepcion) p;
        this.cantidadActualDulces = percepcion.getCantidadAcualDulces();
        this.posicion = percepcion.getPosicionActual();
        this.escenario.setMatriz(percepcion.getMatriz());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EstadoCaperucita)) {
            return false;
        } else {
            return ((EstadoCaperucita) obj).getVidas().equals(this.getVidas()) &&
                    ((EstadoCaperucita) obj).getPosicion().equals(this.getPosicion()) &&
                    ((EstadoCaperucita) obj).getCantidadActualDulces().equals(this.getCantidadActualDulces());
        }
    }

    @Override
    public String toString() {
        return "\n" +
                "- Posición actual en el mapa: Fila: " + posicion.i + ", Columna: " + posicion.j + "\n" +
                "- Vidas restantes: " + vidas + "\n" +
                "- Dulces recolectados: " + cantidadActualDulces + "\n";
    }
}
