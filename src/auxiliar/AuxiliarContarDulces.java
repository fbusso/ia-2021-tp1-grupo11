package auxiliar;

import dominio.Posicion;

@Deprecated
public class AuxiliarContarDulces {

    public static Integer contarDulcesArriba(char[][] matriz, Posicion posicionInicial, Posicion posicionFinal) {
        Integer dulcesEnCamino = 0;
        for (int i = posicionInicial.i + 1; i <= posicionFinal.i; i++) {
            if (matriz[i][posicionFinal.j] == 'D') {
                dulcesEnCamino++;
            }
        }

        return dulcesEnCamino;
    }

    public static Integer contarDulcesAbajo(char[][] matriz, Posicion posicionInicial, Posicion posicionFinal) {
        Integer dulcesEnCamino = 0;
        for (int i = posicionInicial.i - 1; i <= posicionFinal.i; i--) {
            if (matriz[i][posicionFinal.j] == 'D') {
                dulcesEnCamino++;
            }
        }

        return dulcesEnCamino;
    }

    public static Integer contarDulcesDerecha(char[][] matriz, Posicion posicionInicial, Posicion posicionFinal) {
        Integer dulcesEnCamino = 0;
        for (int j = posicionInicial.j + 1; j <= posicionFinal.j; j++) {
            if (matriz[posicionInicial.i][j] == 'D') {
                dulcesEnCamino++;
            }
        }

        return dulcesEnCamino;
    }

    public static Integer contarDulcesIzquierda(char[][] matriz, Posicion posicionInicial, Posicion posicionFinal) {
        Integer dulcesEnCamino = 0;
        for (int j = posicionInicial.j - 1; j <= posicionFinal.j; j--) {
            if (matriz[posicionInicial.i][j] == 'D') {
                dulcesEnCamino++;
            }
        }

        return dulcesEnCamino;
    }
}
