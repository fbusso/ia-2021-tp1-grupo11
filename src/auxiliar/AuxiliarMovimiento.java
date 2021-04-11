package auxiliar;

import dominio.Posicion;

import java.util.ArrayList;

public abstract class AuxiliarMovimiento {

    protected ArrayList<Posicion> posicionesDulces;
    protected Integer cantidadDulcesEnCamino;
    protected Boolean loboEnCamino;
    protected Posicion posicionFinal;

    protected abstract void calcularAuxiliares(char[][] matriz, Posicion posicionActual);
}
