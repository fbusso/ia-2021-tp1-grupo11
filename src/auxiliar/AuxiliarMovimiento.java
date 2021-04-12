package auxiliar;

import dominio.Posicion;

import java.util.ArrayList;
import java.util.List;

public abstract class AuxiliarMovimiento {

    protected ArrayList<Posicion> posicionesDulces;
    protected Integer cantidadDulcesEnCamino;
    protected Boolean loboEnCamino;
    protected Posicion posicionFinal;

    protected abstract void calcularAuxiliares(char[][] matriz, Posicion posicionActual);

    public List<Posicion> getPosicionesDulces() {
        return posicionesDulces;
    }

    public Integer getCantidadDulcesEnCamino() {
        return cantidadDulcesEnCamino;
    }

    public Boolean getLoboEnCamino() {
        return loboEnCamino;
    }

    public Posicion getPosicionFinal() {
        return posicionFinal;
    }

}
