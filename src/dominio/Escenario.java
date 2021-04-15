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
        nuevoEscenario.matriz = escenario.matriz.clone();
        nuevoEscenario.posicionActualLobo = escenario.posicionActualLobo.clone();
        nuevoEscenario.posicionesPosiblesLobo = new ArrayList<Posicion>(escenario.posicionesPosiblesLobo);
        nuevoEscenario.posicionInicialCaperucita = escenario.posicionInicialCaperucita.clone();
        nuevoEscenario.posicionCampoFlores = escenario.posicionCampoFlores;

        // Elimina los dulces recolectados en el nuevo escenario y actualiza el contador.
        for (Posicion posicion : posicionesDulces) {
            nuevoEscenario.matriz[posicion.i][posicion.j] = ' ';
        }
        nuevoEscenario.cantidadDulces = escenario.getCantidadDulces();

        // TODO: Revisar
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

    // TODO: Cambiar
    public static Boolean esCampoDeFlores(Escenario escenario, Posicion posicion) {
        return escenario.posicionCampoFlores.equals(posicion);
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
                    posicionInicialCaperucita = new Posicion(i, j);
                    posicionActualCaperucita = new Posicion(i, j);
                } else if (matriz[i][j] == 'L') {
                    posicionActualLobo = new Posicion(i, j);
                } else if (matriz[i][j] == 'D') {
                    cantidadDulces++;
                } else if (matriz[i][j] == 'F') {
                    posicionCampoFlores = new Posicion(i, j);
                } else if (Escenario.esPosicionPosibleLobo(matriz, i, j)) {
                    posicionesPosiblesLobo.add(new Posicion(i, j));
                }
            }
        }

        // Se mezcla la lista de posiciones para que el lobo aparezca en posiciones aleatorias.
        Collections.shuffle(posicionesPosiblesLobo);

        // TODO: Eliminar. Se usa una lista vacía para probar movimientos
        posicionesPosiblesLobo = Collections.emptyList();
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

    public void setCantidadDulces(Integer cantidadDulces) {
        this.cantidadDulces = cantidadDulces;
    }

    public Posicion getPosicionInicialCaperucita() {
        return posicionInicialCaperucita;
    }

    public void setPosicionInicialCaperucita(Posicion posicionInicialCaperucita) {
        this.posicionInicialCaperucita = posicionInicialCaperucita;
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

    public List<Posicion> getPosicionesPosiblesLobo() {
        return posicionesPosiblesLobo;
    }

    public void setPosicionesPosiblesLobo(List<Posicion> posicionesPosiblesLobo) {
        this.posicionesPosiblesLobo = posicionesPosiblesLobo;
    }

    @Override
    public Escenario clone() {
        Escenario nuevoEscenario = new Escenario();
        nuevoEscenario.matriz = this.matriz;
        nuevoEscenario.posicionesPosiblesLobo = this.posicionesPosiblesLobo;
        nuevoEscenario.posicionActualCaperucita = this.posicionActualCaperucita.clone();
        nuevoEscenario.posicionInicialCaperucita = this.posicionInicialCaperucita.clone();
        nuevoEscenario.cantidadDulces = this.cantidadDulces;
        nuevoEscenario.posicionCampoFlores = this.posicionCampoFlores;
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
