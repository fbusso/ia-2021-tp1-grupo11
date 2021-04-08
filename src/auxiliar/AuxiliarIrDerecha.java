package auxiliar;

import dominio.Posicion;

import java.util.ArrayList;

public class AuxiliarIrDerecha {

    private final ArrayList<Posicion> posicionesDulces;
    private Posicion posicionFinal;
    private Boolean loboEnCamino;
    private Integer cantidadDulcesEnCamino;

    public AuxiliarIrDerecha(char[][] matriz, Posicion posicionActual) {
        // Inicializar valores auxiliares.
        posicionesDulces = new ArrayList<Posicion>();
        cantidadDulcesEnCamino = 0;
        loboEnCamino = false;

        // Calcular valores auxiliares.
        calcularAuxiliares(matriz, posicionActual);
    }

    /**
     * Calcula la siguiente posición de caperucita al avanzar en línea recta hacia la derecha.
     * Calcula la cantidad de dulces que hay en el camino.
     * Verifica si el lobo está en el camino a recorrer.
     *
     * @param matriz:         representación matricial del escenario
     * @param posicionActual: poisición actual de Caperucita
     */
    private void calcularAuxiliares(char[][] matriz, Posicion posicionActual) {
        int indice = posicionActual.j + 1;
        while (indice <= matriz[0].length && matriz[posicionActual.i][indice] != 'A') {
            if (matriz[posicionActual.i][indice] == 'D') {
                posicionesDulces.add(new Posicion(posicionActual.i, indice));
            } else if (matriz[posicionActual.i][indice] == 'L') {
                loboEnCamino = true;
            }
            indice++;
        }
        posicionFinal = new Posicion(posicionActual.i, indice - 1);
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