package dominio;

import java.util.List;

public class Escenario {

    private char[][] matriz;
    private Integer cantidadDulces;
    private Posicion posicionInicialCaperucita;
    private Posicion posicionInicialLobo;

    public Escenario(char[][] matriz) {
        this.matriz = matriz;
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
                    posicionInicialCaperucita = new Posicion(i, j);
                else if (matriz[i][j] == 'L')
                    posicionInicialLobo = new Posicion(i, j);
                else if (matriz[i][j] == 'D')
                    cantidadDulces++;
            }
        }
    }

    public static Boolean esCampoDeFlores(char[][] matriz, Posicion posicion) {
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

    public Posicion getPosicionInicialCaperucita() {
        return posicionInicialCaperucita;
    }

    public Posicion getPosicionInicialLobo() {
        return posicionInicialLobo;
    }

    public void setPosicionInicialLobo(Posicion posicionInicialLobo) {
        this.posicionInicialLobo = posicionInicialLobo;
    }
}
