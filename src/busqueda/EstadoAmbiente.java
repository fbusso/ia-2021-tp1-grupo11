package busqueda;

import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState {

    private Integer cantidadActualVidas;
    private Integer cantidadDulcesRecolectados;
    private Integer cantidadTotalDulces;
    private Escenario escenario;
    private Posicion posicionCaperucita;
    private Posicion posicionActualLobo;

    public EstadoAmbiente(Escenario escenario) {
        this.escenario = escenario;
        this.initState();
    }

    @Override
    public void initState() {
        this.posicionCaperucita = escenario.getPosicionActualCaperucita();
        this.posicionActualLobo = escenario.getPosicionActualLobo();
        this.cantidadDulcesRecolectados = 0;
        this.cantidadActualVidas = 3;
        this.cantidadTotalDulces = escenario.getCantidadDulces();
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

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    public Integer getCantidadActualVidas() {
        return cantidadActualVidas;
    }

    public void setCantidadActualVidas(Integer cantidadActualVidas) {
        this.cantidadActualVidas = cantidadActualVidas;
    }

    public Posicion getPosicionCaperucita() {
        return posicionCaperucita;
    }

    public void setPosicionCaperucita(Posicion posicionCaperucita) {
        this.posicionCaperucita = posicionCaperucita;
    }

    public Posicion getPosicionActualLobo() {
        return posicionActualLobo;
    }

    public void setPosicionActualLobo(Posicion posicionActualLobo) {
        this.posicionActualLobo = posicionActualLobo;
    }

    @Override
    public String toString() {
        return "\n" +
                "- Posición actual de Caperucita: Fila " + posicionCaperucita.i + ", Columna " + posicionCaperucita.j + "\n" +
                "- Posición actual del Lobo: Fila " + posicionActualLobo.i + ", Columna " + posicionActualLobo.j + "\n" +
                "- Cantidad de dulces a recolectar restantes: " + (cantidadTotalDulces - cantidadDulcesRecolectados);
    }
}
