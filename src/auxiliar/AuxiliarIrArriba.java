package auxiliar;

import dominio.Posicion;

import java.util.ArrayList;

public class AuxiliarIrArriba {

    private Posicion posicionFinal;
    private Boolean loboEnCamino;
    private ArrayList<Posicion> posicionesDulces;
    private Integer cantidadDulcesEnCamino;

    public AuxiliarIrArriba(char[][] matriz, Posicion posicionActual) {
        posicionesDulces = new ArrayList<Posicion>();
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

    /**
     * Calcula la siguiente posición de caperucita al avanzar en línea recta hacia arriba.
     * Calcula la cantidad de dulces que hay en el camino.
     * Verifica si el lobo está en el camino a recorrer.
     * <p>
     * La lectura de la matriz es de izquierda a derecha y de arriba hacia abajo, por lo que
     * para moverse "hacia arriba" hay que restar en el valor de la fila.
     *
     * @param matriz
     * @param posicionActual
     */
    private void calcularAuxiliares(char[][] matriz, Posicion posicionActual) {
        int indice = posicionActual.i - 1;
        while (matriz[indice][posicionActual.j] != 'A') {
            if (matriz[indice][posicionActual.j] == 'D') {
                posicionesDulces.add(new Posicion(indice, posicionActual.j));
            } else if (matriz[indice][posicionActual.j] == 'L') {
                loboEnCamino = true;
            }
            indice--;
        }
        posicionFinal = new Posicion(indice + 1, posicionActual.j);
        cantidadDulcesEnCamino = posicionesDulces.size();
    }
}
