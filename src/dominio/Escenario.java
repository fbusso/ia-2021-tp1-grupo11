package dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Escenario {

    private Integer cantidadDulces;
    private char[][] matriz;
    private Posicion posicionActualCaperucita;
    private Posicion posicionActualLobo;
    private Posicion posicionCampoFlores;
    private Posicion posicionInicialCaperucita;
    private List<Posicion> posicionesDulces;
    private List<Posicion> posicionesPosiblesLobo;

    private Escenario() {

    }

    public Escenario(char[][] matriz) {
        this.matriz = matriz;
        posicionesDulces = new ArrayList<>();
        posicionesPosiblesLobo = new ArrayList<>();
        calculosAuxiliares();
    }

    /**
     * Se debe crear un escenario nuevo en 2 casos, cuado se realiza un movimiento normal, o cuando se realiza un
     * movimiento y se pierde una vida. En este método se encapsulan las acciones comunes al clonado de un escenario
     * en ambos casos. Además del pasaje de atributos, estas acciones son:
     * - Reemplazar la posición actual de caperucita con un espacio en blanco.
     * - Colocar un caracter 'C' en la posición pasada como parámetro.
     * - Reemplazar la posición actual del lobo con un espacio en blanco.
     * - Colocar un caracter 'L' en una nueva posición de la lista de posiciones posibles.
     *
     * @param escenario     escenario a copiar
     * @param posicionFinal posición final de caperucita en el escenario nuevo
     * @return escenario copiado
     */
    private static Escenario copiar(Escenario escenario, Posicion posicionFinal) {
        Escenario nuevoEscenario = new Escenario();

        // Pasaje de atributos básicos.
        nuevoEscenario.matriz = Escenario.copiarMatriz(escenario.matriz);
        nuevoEscenario.cantidadDulces = escenario.cantidadDulces;
        nuevoEscenario.posicionInicialCaperucita = escenario.posicionInicialCaperucita;
        nuevoEscenario.posicionCampoFlores = escenario.posicionCampoFlores;
        nuevoEscenario.posicionActualLobo = escenario.posicionActualLobo;
        nuevoEscenario.posicionesPosiblesLobo = new ArrayList<>(escenario.posicionesPosiblesLobo);
        nuevoEscenario.posicionesDulces = escenario.posicionesDulces;

        // Se reemplaza la posición actual de Caperucita por una celda vacía.
        nuevoEscenario.matriz[escenario.posicionActualCaperucita.i][escenario.posicionActualCaperucita.j] = ' ';
        // Se actualiza la posición actual de Caperucita.
        nuevoEscenario.posicionActualCaperucita = posicionFinal;
        // Se actualiza la celda correspondiente a la nueva posición de Caperucita.
        nuevoEscenario.matriz[posicionFinal.i][posicionFinal.j] = 'C';

        // Se reemplaza la posición actual del lobo por una celda vacía.
        nuevoEscenario.matriz[nuevoEscenario.posicionActualLobo.i][nuevoEscenario.posicionActualLobo.j] = ' ';

        // Se agrega la posición previa del lobo como una nueva posición posible.
        nuevoEscenario.posicionesPosiblesLobo.add(new Posicion(escenario.posicionActualLobo.i, escenario.posicionActualLobo.j));

        // Se elige la primer posición posible de la lista de posiciones mezclada.
        if (!nuevoEscenario.posicionesPosiblesLobo.isEmpty()) {
            Posicion nuevaPosicionLobo = nuevoEscenario.posicionesPosiblesLobo.remove(0);
            nuevoEscenario.matriz[nuevaPosicionLobo.i][nuevaPosicionLobo.j] = 'L';
            nuevoEscenario.posicionActualLobo = nuevaPosicionLobo;
        }

        return nuevoEscenario;
    }

    /**
     * Retorna un escenario modificado, producido por un movimiento de caperucita.
     *
     * @param escenario               escenario actual a obtener modificado.
     * @param nuevaPosicionCaperucita posicion de Caperucita luego de realizar el movimiento.
     * @param posicionesDulces        posiciones de los dulces recolectados en el movimiento.
     */
    public static Escenario obtenerEscenarioActualizado(Escenario escenario, Posicion nuevaPosicionCaperucita, List<Posicion> posicionesDulces) {
        // Obtiene el escenario base a copiar.
        Escenario nuevoEscenario = Escenario.copiar(escenario, nuevaPosicionCaperucita);

        // Elimina los dulces recolectados en el nuevo escenario.
        for (Posicion posicion : posicionesDulces) {
            nuevoEscenario.matriz[posicion.i][posicion.j] = ' ';
        }

        return nuevoEscenario;
    }

    /**
     * Retorna un escenario modificado, producido cuando Caperucita pierde una vida.
     *
     * @param escenario escenario original desde el que se parte para obtener el escenario nuevo.
     * @return escenario reiniciado (Caperucita en la posición inicial y dulces restaurados).
     */
    public static Escenario obtenerEscenarioReiniciado(Escenario escenario) {
        // Obtiene el escenario base a copiar.
        Escenario nuevoEscenario = Escenario.copiar(escenario, escenario.posicionInicialCaperucita);

        // Restaura los dulces en el nuevo escenario.
        for (Posicion posicion : nuevoEscenario.posicionesDulces) {
            nuevoEscenario.matriz[posicion.i][posicion.j] = 'D';
        }

        return nuevoEscenario;
    }

    public static Boolean esCampoDeFlores(Escenario escenario, Posicion posicion) {
        return posicion.equals(escenario.posicionCampoFlores);
    }

    /**
     * Determina si una posición cumple las condiciones para que el lobo aparezca en ella.
     * Se implementa utilizando Excepciones para no contemplar casos particulares en valores límite de la matriz.
     *
     * @param matriz matriz de escenario
     * @param i      índice correspondiente a la fila.
     * @param j      índice correspondiente a la columna.
     * @return si la posición es apta para que el lobo aparezca en ella.
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

    /**
     * Crea una copia profunda de una matriz.
     * (El método clone() no funciona para estos casos porque mantiene algunas referencias).
     *
     * @param matrizOriginal matriz a copiar
     * @return copia de la matriz
     */
    public static char[][] copiarMatriz(char[][] matrizOriginal) {
        return Arrays.stream(matrizOriginal).map(char[]::clone).toArray($ -> matrizOriginal.clone());
    }

    public static Escenario clonar(Escenario escenario) {
        Escenario nuevoEscenario = new Escenario();
        nuevoEscenario.matriz = Escenario.copiarMatriz(escenario.matriz);
        nuevoEscenario.posicionesPosiblesLobo = new ArrayList<>(escenario.posicionesPosiblesLobo);
        nuevoEscenario.posicionInicialCaperucita = escenario.posicionInicialCaperucita;
        nuevoEscenario.posicionesDulces = new ArrayList<>(escenario.posicionesDulces);
        nuevoEscenario.posicionActualCaperucita = escenario.posicionActualCaperucita;
        nuevoEscenario.cantidadDulces = escenario.cantidadDulces;
        nuevoEscenario.posicionCampoFlores = escenario.posicionCampoFlores.clone();
        nuevoEscenario.posicionActualLobo = escenario.posicionActualLobo.clone();

        return nuevoEscenario;
    }

    /**
     * Cálculos auxiliares asociados a la primer lectura de una matriz (desde un archivo de texto),
     */
    private void calculosAuxiliares() {
        cantidadDulces = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == 'C') {
                    posicionActualCaperucita = new Posicion(i, j);
                    posicionInicialCaperucita = new Posicion(i, j);
                } else if (matriz[i][j] == 'L') {
                    posicionActualLobo = new Posicion(i, j);
                } else if (matriz[i][j] == 'D') {
                    cantidadDulces++;
                    posicionesDulces.add(new Posicion(i, j));
                } else if (matriz[i][j] == 'F') {
                    posicionCampoFlores = new Posicion(i, j);
                } else if (Escenario.esPosicionPosibleLobo(matriz, i, j)) {
                    posicionesPosiblesLobo.add(new Posicion(i, j));
                }
            }
        }

        // Se mezcla la lista de posiciones para que el lobo aparezca en posiciones aleatorias.
        Collections.shuffle(posicionesPosiblesLobo);
    }

    public Integer getCantidadDulces() {
        return cantidadDulces;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public Posicion getPosicionActualCaperucita() {
        return posicionActualCaperucita;
    }

    public void setPosicionActualCaperucita(Posicion posicionActualCaperucita) {
        this.posicionActualCaperucita = posicionActualCaperucita;
    }

    public Posicion getPosicionActualLobo() {
        return posicionActualLobo;
    }

    @Override
    public String toString() {
        StringBuilder matrizStringBuilder = new StringBuilder("MATRIZ: \n");
        for (char[] linea : this.matriz) {
            matrizStringBuilder.append((Arrays.toString(linea))).append('\n');
        }
        return matrizStringBuilder.toString();
    }

}
