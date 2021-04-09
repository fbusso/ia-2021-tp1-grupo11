package dominio;

import auxiliar.AuxiliarCsv;

import java.util.List;

public class Escenario {

    private char[][] matriz;
    private Integer cantidadDulces;
    private Posicion posicionIncial;

    public Escenario() {
        matriz = AuxiliarCsv.obtenerMatriz();
        calculosAuxiliares();
    }

    public static char[][] removerDulces(char[][] matriz, List<Posicion> posiciones) {
        for (Posicion posicion : posiciones) {
            matriz[posicion.i][posicion.j] = ' ';
        }
        return matriz;
    }

    private void calculosAuxiliares() {
        cantidadDulces = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 'C')
                    posicionIncial = new Posicion(i, j);
                if (matriz[i][j] == 'D')
                    cantidadDulces++;
            }
        }
    }

    public Boolean esCampoDeFlores(Posicion posicion) {
        return matriz[posicion.i][posicion.j] == 'F';
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public Integer getCantidadDulces() {
        return cantidadDulces;
    }

    public Posicion getPosicionIncial() {
        return posicionIncial;
    }

}
