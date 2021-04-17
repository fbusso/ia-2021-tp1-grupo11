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

    public static char[][] removerDulces(char[][] matriz, List<Posicion> posiciones) {
        for (Posicion posicion : posiciones) {
            matriz[posicion.i][posicion.j] = ' ';
        }

        return matriz;
    }

    /**
     * Retorna un escenario modificado, producido por un movimiento de caperucita.
     *
     * @param escenario               escenario actual a obtener modificado.
     * @param nuevaPosicionCaperucita posicion de Caperucita luego de realizar el movimiento.
     * @param posicionesDulces        posiciones de los dulces recolectados en el movimiento.
     */
    public static Escenario obtenerEscenarioActualizado(Escenario escenario, Posicion nuevaPosicionCaperucita, List<Posicion> posicionesDulces) {

        Escenario nuevoEscenario = new Escenario();

        // Clonar las propiedades del escenario viejo en el nuevo escenario.
        // No es necesario copiar la cantidad de dulces, dado que va a ser sobreescrita.
        nuevoEscenario.matriz = Escenario.clonarMatriz(escenario.matriz);
        nuevoEscenario.posicionActualLobo = escenario.posicionActualLobo.clone();
        nuevoEscenario.posicionesPosiblesLobo = new ArrayList<>(escenario.posicionesPosiblesLobo);
        nuevoEscenario.posicionCampoFlores = escenario.posicionCampoFlores.clone();
        nuevoEscenario.posicionesDulces = new ArrayList<>(escenario.posicionesDulces);
        nuevoEscenario.posicionInicialCaperucita = escenario.posicionInicialCaperucita;

        // Elimina los dulces recolectados en el nuevo escenario y actualiza el contador.
        for (Posicion posicion : posicionesDulces) {
            nuevoEscenario.matriz[posicion.i][posicion.j] = ' ';
        }

        // Se reemplaza la posición actual de Caperucita por una celda vacía.
        nuevoEscenario.matriz[escenario.posicionActualCaperucita.i][escenario.posicionActualCaperucita.j] = ' ';

        // Se actualiza la posición actual de caperucita
        nuevoEscenario.setPosicionActualCaperucita(nuevaPosicionCaperucita.clone());
        nuevoEscenario.matriz[nuevaPosicionCaperucita.i][nuevaPosicionCaperucita.j] = 'C';

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

    public static Escenario obtenerEscenarioReiniciado(Escenario escenario) {
        Escenario nuevoEscenario = new Escenario();
        nuevoEscenario.matriz = Escenario.clonarMatriz(escenario.matriz);
        nuevoEscenario.cantidadDulces = escenario.cantidadDulces;
        nuevoEscenario.posicionInicialCaperucita = escenario.posicionInicialCaperucita;
        nuevoEscenario.posicionCampoFlores = escenario.posicionCampoFlores;
        nuevoEscenario.posicionActualLobo = escenario.posicionActualLobo;
        nuevoEscenario.posicionesPosiblesLobo = new ArrayList<>(escenario.posicionesPosiblesLobo);
        nuevoEscenario.posicionesDulces = escenario.posicionesDulces;

        nuevoEscenario.matriz[escenario.posicionActualCaperucita.i][escenario.posicionActualCaperucita.j] = ' ';
        nuevoEscenario.setPosicionActualCaperucita(escenario.posicionInicialCaperucita);
        nuevoEscenario.matriz[escenario.posicionActualCaperucita.i][escenario.posicionActualCaperucita.j] = 'C';

        for (Posicion posicion : nuevoEscenario.posicionesDulces) {
            nuevoEscenario.matriz[posicion.i][posicion.j] = 'D';
        }

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

    // FIXME: Leer bien las cosas
    public static Boolean esCampoDeFlores(Escenario escenario, Posicion posicion) {
        return posicion.equals(new Posicion(7, 7));
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

    public static char[][] clonarMatriz(char[][] matrizOriginal) {
        return Arrays.stream(matrizOriginal).map(char[]::clone).toArray($ -> matrizOriginal.clone());
    }

    @Deprecated
    public char[][] actualizarEscenario(List<Posicion> posicionesDulces) {
        for (Posicion posicion : posicionesDulces) {
            matriz[posicion.i][posicion.j] = ' ';
        }
        return matriz;
    }

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

    public void setCantidadDulces(Integer cantidadDulces) {
        this.cantidadDulces = cantidadDulces;
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

    public void setPosicionActualLobo(Posicion posicionActualLobo) {
        this.posicionActualLobo = posicionActualLobo;
    }

    public Posicion getPosicionCampoFlores() {
        return posicionCampoFlores;
    }

    public void setPosicionCampoFlores(Posicion posicionCampoFlores) {
        this.posicionCampoFlores = posicionCampoFlores;
    }

    public Posicion getPosicionInicialCaperucita() {
        return posicionInicialCaperucita;
    }

    public void setPosicionInicialCaperucita(Posicion posicionInicialCaperucita) {
        this.posicionInicialCaperucita = posicionInicialCaperucita;
    }

    public List<Posicion> getPosicionesDulces() {
        return posicionesDulces;
    }

    public void setPosicionesDulces(List<Posicion> posicionesDulces) {
        this.posicionesDulces = posicionesDulces;
    }

    public List<Posicion> getPosicionesPosiblesLobo() {
        return posicionesPosiblesLobo;
    }

    public void setPosicionesPosiblesLobo(List<Posicion> posicionesPosiblesLobo) {
        this.posicionesPosiblesLobo = posicionesPosiblesLobo;
    }

    @Override
    public Escenario clone() {
        Escenario nuevoEscenario = new Escenario();
        nuevoEscenario.matriz = Escenario.clonarMatriz(this.matriz);
        nuevoEscenario.posicionesPosiblesLobo = new ArrayList<>(this.posicionesPosiblesLobo);
        nuevoEscenario.posicionInicialCaperucita = this.posicionInicialCaperucita;
        nuevoEscenario.posicionesDulces = new ArrayList<>(this.posicionesDulces);
        nuevoEscenario.posicionActualCaperucita = this.posicionActualCaperucita;
        nuevoEscenario.cantidadDulces = this.cantidadDulces;
        nuevoEscenario.posicionCampoFlores = this.posicionCampoFlores.clone();
        nuevoEscenario.posicionActualLobo = this.posicionActualLobo.clone();

        return nuevoEscenario;
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
