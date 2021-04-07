package auxiliar;

import dominio.Posicion;

@Deprecated
public class AuxiliarPosicionSiguiente {

    public static Posicion posicionSiguienteArriba(char[][] escenario, Posicion posicionActual) {
        int indice = posicionActual.i;
        while (escenario[indice + 1][posicionActual.j] != 'A') {
            indice++;
        }
        return new Posicion(indice, posicionActual.j);
    }

    public static Posicion posicionSiguienteAbajo(char[][] escenario, Posicion posicionActual) {
        int indice = posicionActual.i;
        while (escenario[indice - 1][posicionActual.j] != 'A') {
            indice--;
        }
        return new Posicion(indice, posicionActual.j);
    }

    public static Posicion posicionSiguienteDerecha(char[][] escenario, Posicion posicionActual) {
        int indice = posicionActual.j;
        while (escenario[posicionActual.i][indice + 1] != 'A') {
            indice++;
        }
        return new Posicion(posicionActual.i, indice);
    }

    public static Posicion posicionSiguienteIzquierda(char[][] escenario, Posicion posicionActual) {
        int indice = posicionActual.j;
        while (escenario[posicionActual.i][indice - 1] != 'A') {
            indice--;
        }
        return new Posicion(posicionActual.i, indice);
    }
}
