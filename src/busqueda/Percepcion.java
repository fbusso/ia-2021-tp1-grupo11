package busqueda;

import dominio.Posicion;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class Percepcion extends Perception {

    private char[][] matriz;
    private Integer cantidadAcualDulces;
    private Posicion posicionActual;

    public Percepcion() {
        matriz = null;
        posicionActual = null;
        posicionActual = null;
    }

    public Percepcion(Agent agente, Environment ambiente) {
        super(agente, ambiente);
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) environment.getEnvironmentState();
        posicionActual = estadoAmbiente.getPosicionCaperucita();
        cantidadAcualDulces = estadoAmbiente.getCantidadTotalDulces();
        matriz = estadoAmbiente.getEscenario().getMatriz();
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public Integer getCantidadAcualDulces() {
        return cantidadAcualDulces;
    }

    public void setCantidadAcualDulces(Integer cantidadAcualDulces) {
        this.cantidadAcualDulces = cantidadAcualDulces;
    }

    public Posicion getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(Posicion posicionActual) {
        this.posicionActual = posicionActual;
    }

    @Override
    public String toString() {
        return "Posicion actual de Caperucita: Fila " + posicionActual.i + ", Columna " + posicionActual.j;
    }
}
