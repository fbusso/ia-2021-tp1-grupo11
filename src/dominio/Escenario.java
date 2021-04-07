package dominio;

import java.util.List;

public class Escenario {
    private char[][] matriz;
    private Integer cantidadDulces;

    public Escenario(Integer cantidadFilas, Integer cantidadColumnas) {
        this.matriz = new char[cantidadFilas][cantidadColumnas];
        this.cantidadDulces = contarDulces();
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

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public Integer getCantidadDulces() {
        return this.cantidadDulces;
    }

    public Boolean esCampoDeFlores(Posicion posicion) {
        return matriz[posicion.i][posicion.j] == 'F';
    }

    public static char[][] removerDulces(char[][] matriz, List<Posicion> posiciones) {
        for (Posicion posicion : posiciones) {
            matriz[posicion.i][posicion.j] = ' ';
        }
        return matriz;
    }
}
