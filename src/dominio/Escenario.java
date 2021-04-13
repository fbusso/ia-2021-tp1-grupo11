package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Escenario {

    private char[][] matriz;
    private Integer cantidadDulces;
    private Posicion posicionInicialCaperucita;
    private Posicion posicionActualLobo;
    private List<Posicion> posicionesPosiblesLobo;

    private Escenario() {

    }

    public Escenario(char[][] matriz) {
        this.matriz = matriz;
        posicionesPosiblesLobo = new ArrayList<Posicion>();
        calculosAuxiliares();
    }

    public static char[][] removerDulces(char[][] matriz, List<Posicion> posiciones) {
        for (Posicion posicion : posiciones) {
            matriz[posicion.i][posicion.j] = ' ';
        }

        return matriz;
    }

    public char[][] actualizarEscenario(List<Posicion> posicionesDulces) {
        for (Posicion posicion : posicionesDulces) {
            matriz[posicion.i][posicion.j] = ' ';
        }

        matriz[posicionActualLobo.i][posicionActualLobo.j] = ' ';
        Posicion nuevaPosicionLobo = posicionesPosiblesLobo.get(new Random().nextInt(posicionesPosiblesLobo.size()));
        posicionesPosiblesLobo.add(posicionActualLobo);
        matriz[nuevaPosicionLobo.i][nuevaPosicionLobo.j] = 'L';
        posicionActualLobo = nuevaPosicionLobo;

        return matriz;
    }

    public static Escenario obtenerEscenarioActualizado(Escenario escenario, List<Posicion> posicionesDulces) {
        Escenario nuevoEscenario = new Escenario();

        // Clonar las propiedades del escenario viejo en el nuevo escenario.
        // No es necesario copiar la cantidad de dulces, dado que va a ser sobreescrita.
        nuevoEscenario.matriz = escenario.matriz;
        nuevoEscenario.posicionActualLobo = escenario.posicionActualLobo;
        nuevoEscenario.posicionesPosiblesLobo = escenario.posicionesPosiblesLobo;
        nuevoEscenario.posicionInicialCaperucita = escenario.posicionInicialCaperucita;

        // Actualizar las posiciones de los dulces en el nuevo escenario
        for (Posicion posicion : posicionesDulces) {
            nuevoEscenario.matriz[posicion.i][posicion.j] = ' ';
        }

        nuevoEscenario.cantidadDulces = escenario.getCantidadDulces() - posicionesDulces.size();

        // Se reemplaza la posición actual del lobo por una celda vacía.
        nuevoEscenario.matriz[nuevoEscenario.posicionActualLobo.i][nuevoEscenario.posicionActualLobo.j] = ' ';

        // Se agrega la posición previa del lobo como una nueva posición posible.
        nuevoEscenario.posicionesPosiblesLobo.add(new Posicion(escenario.posicionActualLobo.i, escenario.posicionActualLobo.j));

        // De las posibles posiciones del lobo, se elige una de manera aleatoria como nueva posición.
        Posicion nuevaPosicionLobo = nuevoEscenario.posicionesPosiblesLobo.get(new Random().nextInt(nuevoEscenario.posicionesPosiblesLobo.size()));
        nuevoEscenario.matriz[nuevaPosicionLobo.i][nuevaPosicionLobo.j] = 'L';
        nuevoEscenario.posicionActualLobo = nuevaPosicionLobo;

        return nuevoEscenario;
    }


    private void calculosAuxiliares() {
        cantidadDulces = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 'C')
                    posicionInicialCaperucita = new Posicion(i, j);
                else if (matriz[i][j] == 'L')
                    posicionActualLobo = new Posicion(i, j);
                else if (matriz[i][j] == 'D')
                    cantidadDulces++;
                else if (Escenario.esPosicionPosibleLobo(matriz, i, j))
                    posicionesPosiblesLobo.add(new Posicion(i, j));
            }
        }
    }

    public static Boolean esCampoDeFlores(char[][] matriz, Posicion posicion) {
        return matriz[posicion.i][posicion.j] == 'F';
    }


    /**
     * Determina si una posición cumple las condiciones para que el lobo aparezca en ella.
     * Se implementa utilizando Excepciones para no contemplar casos particulares en valores límite de la matriz
     *
     * @param matriz: matriz de escenario
     * @param i:      índice correspondiente a la fila.
     * @param j:      índice correspondiente a la columna.
     * @return: si la posición es apta para que el lobo aparezca en ella.
     */
    private static Boolean esPosicionPosibleLobo(char[][] matriz, Integer i, Integer j) {
        boolean esPosicionPosible = false;
        try {
            esPosicionPosible = matriz[i][j] != 'A' &&
                    (matriz[i + 1][j] == 'A' || matriz[i - 1][j] == 'A' || matriz[i][j + 1] == 'A' || matriz[i][j - 1] == 'A');

        } catch (IndexOutOfBoundsException ignored) {
        }

        return esPosicionPosible;
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

    public Posicion getPosicionActualLobo() {
        return posicionActualLobo;
    }

    public void setPosicionActualLobo(Posicion posicionActualLobo) {
        this.posicionActualLobo = posicionActualLobo;
    }
}
