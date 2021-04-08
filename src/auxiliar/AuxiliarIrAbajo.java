package auxiliar;

import dominio.Posicion;

import java.util.ArrayList;

public class AuxiliarIrAbajo {

    private final ArrayList<Posicion> posicionesDulces;
    private Posicion posicionFinal;
    private Boolean loboEnCamino;
    private Integer cantidadDulcesEnCamino;

    public AuxiliarIrAbajo(char[][] matriz, Posicion posicionActual) {
        // Inicializar valores auxiliares.
        posicionesDulces = new ArrayList<Posicion>();
        cantidadDulcesEnCamino = 0;
        loboEnCamino = false;

        // Calcular valores auxiliares.
        calcularAuxiliares(matriz, posicionActual);
    }

    /**
     * Calcula la siguiente posición de caperucita al avanzar en línea recta hacia abajo.
     * Calcula la cantidad de dulces que hay en el camino.
     * Verifica si el lobo está en el camino a recorrer.
     * <p>
     * La lectura de la matriz es de izquierda a derecha y de arriba hacia abajo, por lo que
     * para moverse "hacia abajo" hay que sumar en el valor de la fila.
     *
     * @param matriz:         representación matricial del escenario
     * @param posicionActual: poisición actual de Caperucita
     */
    private void calcularAuxiliares(char[][] matriz, Posicion posicionActual) {
        int indice = posicionActual.i + 1;
        while (indice < matriz.length && matriz[indice][posicionActual.j] != 'A') {
            if (matriz[indice][posicionActual.j] == 'D') {
                posicionesDulces.add(new Posicion(indice, posicionActual.j));
            } else if (matriz[indice][posicionActual.j] == 'L') {
                loboEnCamino = true;
            }
            indice++;
        }
        posicionFinal = new Posicion(indice - 1, posicionActual.j);
        cantidadDulcesEnCamino = posicionesDulces.size();
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
}