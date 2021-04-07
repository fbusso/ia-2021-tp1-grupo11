package auxiliar;

import dominio.Posicion;

@Deprecated
public class AuxiliarPuedeAvanzar {

    public static Boolean puedeAvanzarArriba(char[][] matriz, Posicion posicionActual) {
        int indice = posicionActual.i + 1;
        while (matriz[indice][posicionActual.j] != 'A') {
            if (matriz[indice][posicionActual.j] == 'L') {
                return false;
            }
            indice++;
        }
        return true;
    }

    public static Boolean puedeAvanzarAbajo(char[][] escenario, Posicion posicionActual) {
        int indice = posicionActual.i - 1;
        while (escenario[indice][posicionActual.j] != 'A') {
            if (escenario[indice][posicionActual.j] == 'L') {
                return false;
            }
            indice--;
        }
        return true;
    }

    public static Boolean puedeAvanzarDerecha(char[][] escenario, Posicion posicionActual) {
        int indice = posicionActual.j + 1;
        while (escenario[posicionActual.i][indice] != 'A') {
            if (escenario[posicionActual.i][indice] == 'L') {
                return false;
            }
            indice++;
        }
        return true;
    }

    public static Boolean puedeAvanzarIzquierda(char[][] escenario, Posicion posicionActual) {
        int indice = posicionActual.j - 1;
        while (escenario[posicionActual.i][indice] != 'A') {
            if (escenario[posicionActual.i][indice] == 'L') {
                return false;
            }
            indice--;
        }
        return true;
    }
}
