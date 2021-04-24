package busqueda;

import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.HashMap;
import java.util.Map;

public class EstadoCaperucita extends SearchBasedAgentState implements Cloneable {

    private Integer cantidadActualDulces;
    private Integer cantidadTotalDulces;
    private Escenario escenario;
    private Posicion posicion;
    private Map<Posicion, Integer> posicionesVisitadas;
    private Posicion ultimaPosicionVisitada;
    private Integer vidas;

    public EstadoCaperucita() {
    }

    public EstadoCaperucita(Escenario escenario) {
        this.escenario = escenario;
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

    public Posicion getUltimaPosicionVisitada() {
        return ultimaPosicionVisitada;
    }

    public void setUltimaPosicionVisitada(Posicion ultimaPosicionVisitada) {
        this.ultimaPosicionVisitada = ultimaPosicionVisitada;
    }


    public Map<Posicion, Integer> getPosicionesVisitadas() {
        return posicionesVisitadas;
    }

    public void setPosicionesVisitadas(Map<Posicion, Integer> posicionesVisitadas) {
        this.posicionesVisitadas = posicionesVisitadas;
    }

    @Override
    public SearchBasedAgentState clone() {
        EstadoCaperucita nuevoEstado = new EstadoCaperucita();
        nuevoEstado.setVidas(this.vidas);
        nuevoEstado.setEscenario(Escenario.clonar(this.escenario));
        nuevoEstado.setCantidadActualDulces(this.cantidadActualDulces);
        nuevoEstado.setCantidadTotalDulces(this.cantidadTotalDulces);
        nuevoEstado.setPosicion(this.posicion.clone());
        nuevoEstado.setPosicionesVisitadas(this.posicionesVisitadas);
        return nuevoEstado;
    }

    public boolean posicionVisitadaVeces(Posicion posicion, Integer cantidadMaximaVisitas) {
        return !this.posicionesVisitadas.containsKey(posicion) || this.posicionesVisitadas.get(posicion) <= cantidadMaximaVisitas;
    }

    public void actualizarPosicionesVisitadas(Posicion posicion) {
        if (this.posicionesVisitadas.containsKey(posicion)) {
            Integer cantidadVisitas = this.posicionesVisitadas.get(posicion);
            this.posicionesVisitadas.replace(posicion, cantidadVisitas + 1);
        } else {
            this.posicionesVisitadas.put(posicion, 1);
        }
    }

    @Override
    public void initState() {
        this.vidas = 3;
        this.cantidadActualDulces = 0;
        this.posicion = escenario.getPosicionActualCaperucita();
        this.cantidadTotalDulces = escenario.getCantidadDulces();
        this.ultimaPosicionVisitada = null;
        this.posicionesVisitadas = new HashMap<>();
        this.posicionesVisitadas.put(escenario.getPosicionActualCaperucita(), 1);
    }

    @Override
    public void updateState(Perception p) {
        Percepcion percepcion = (Percepcion) p;
        this.escenario = percepcion.getEscenario();
    }

    /**
     * Dos estados son iguales si se cumplen las siguientes condiciones:
     * 1. La cantidad de vidas es la misma
     * 2. La posición es la misma
     * 3. La cantidad de dulces recolectadas es la misma
     *
     * @param estado estado actual de Caperucita.
     * @return si dos estados son iguales.
     */
    @Override
    public boolean equals(Object estado) {
        return (estado instanceof EstadoCaperucita) &&
                ((EstadoCaperucita) estado).getVidas().equals(this.getVidas()) &&
                ((EstadoCaperucita) estado).getPosicion().equals(this.getPosicion()) &&
                ((EstadoCaperucita) estado).getCantidadActualDulces().equals(this.getCantidadActualDulces());
    }

    @Override
    public String toString() {
        return "\n" +
                "- Posición actual en el mapa: Fila: " + posicion.i + ", Columna: " + posicion.j + "\n" +
                "- Vidas restantes: " + vidas + "\n" +
                "- Dulces recolectados: " + cantidadActualDulces + "\n";
    }

}
