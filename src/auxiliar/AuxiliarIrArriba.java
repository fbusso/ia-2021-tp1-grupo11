package auxiliar;

import dominio.Posicion;

import java.util.ArrayList;

public class AuxiliarIrArriba {


    private Posicion posicionFinal;
    private Boolean loboEnCamino;
    private ArrayList<Posicion> posicionesDulces;
    private Integer cantidadDulcesEnCamino;

    public AuxiliarIrArriba(char[][] matriz, Posicion posicionActual) {
        posicionesDulces = new ArrayList<>();
        cantidadDulcesEnCamino = 0;
        loboEnCamino = false;
        calcularAuxiliares(matriz, posicionActual);
    }

    public Posicion getPosicionFinal() {
        return posicionFinal;
    }

    public Boolean getLoboEnCamino() {
        return loboEnCamino;
    }

    public ArrayList<Posicion> getPosicionesDulces() {
        return posicionesDulces;
    }

    public Integer getCantidadDulcesEnCamino() {
        return cantidadDulcesEnCamino;
    }

    private void calcularAuxiliares(char[][] matriz, Posicion posicionActual) {
        int indice = posicionActual.i + 1;
        while (matriz[indice][posicionActual.j] != 'A') {
            if (matriz[indice][posicionActual.j] == 'D') {
                posicionesDulces.add(new Posicion(indice, posicionActual.j));
            } else if (matriz[indice][posicionActual.j] == 'L') {
                loboEnCamino = true;
            }
            indice++;
        }
        posicionFinal = new Posicion(indice - 1, posicionFinal.j);
        cantidadDulcesEnCamino = posicionesDulces.size();
    }
}
