package auxiliar;

import dominio.Posicion;

import java.util.ArrayList;

public class AuxiliarIrArriba extends AuxiliarMovimiento {

    public AuxiliarIrArriba(char[][] matriz, Posicion posicionActual) {
        // Inicializar valores auxiliares.
        posicionesDulces = new ArrayList<Posicion>();
        cantidadDulcesEnCamino = 0;
        loboEnCamino = false;

        // Calcular valores auxiliares.
        calcularAuxiliares(matriz, posicionActual);
    }

    /**
     * Calcula la siguiente posición de caperucita al avanzar en línea recta hacia arriba.
     * Calcula la cantidad de dulces que hay en el camino.
     * Verifica si el lobo está en el camino a recorrer.
     * <p>
     * La lectura de la matriz es de izquierda a derecha y de arriba hacia abajo, por lo que
     * para moverse "hacia arriba" hay que restar en el valor de la fila.
     *
     * @param matriz:         representación matricial del escenario
     * @param posicionActual: posición actual de Caperucita
     */
    private void calcularAuxiliares(char[][] matriz, Posicion posicionActual) {
        int indice = posicionActual.i - 1;
        while (indice >= 0 && matriz[indice][posicionActual.j] != 'A') {
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
