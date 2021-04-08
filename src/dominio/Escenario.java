package dominio;

import auxiliar.AuxiliarCsv;

import java.util.List;

public class Escenario {
    private char[][] matriz;
    private final Integer cantidadDulces;

    public Escenario() {
        this.matriz = AuxiliarCsv.obtenerMatriz();
        this.cantidadDulces = contarDulces();
    }

    public static char[][] removerDulces(char[][] matriz, List<Posicion> posiciones) {
        for (Posicion posicion : posiciones) {
            matriz[posicion.i][posicion.j] = ' ';
        }
        return matriz;
    }

    private Integer contarDulces() {
        Integer cantidadDulces = 0;
        for (char[] fila : matriz) {
            for (char celda : fila) {
                if (celda == 'D')
                    cantidadDulces++;
            }
        }

        return cantidadDulces;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public Integer getCantidadDulces() {
        return this.cantidadDulces;
    }

    public Boolean esCampoDeFlores(Posicion posicion) {
        return matriz[posicion.i][posicion.j] == 'F';
    }

    public Posicion obtenerPosicionInicial() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 14; j++) {
                if (matriz[i][j] == 'C')
                    return new Posicion(i, j);
            }
        }
        return null;
    }
}
