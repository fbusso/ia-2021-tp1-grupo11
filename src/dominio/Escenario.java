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
    private Posicion posicionInicialCaperucita;
    private List<Posicion> posicionesCampoFlores;
    private List<Posicion> posicionesDulces;
    private List<Posicion> posicionesPosiblesLobo;

    private Escenario() {

    }

    public Escenario(char[][] matriz) {
        this.matriz = matriz;
        posicionesDulces = new ArrayList<>();
        posicionesCampoFlores = new ArrayList<>();
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
     * @param escenario               escenario a copiar
     * @param nuevaPosicionCaperucita posición final de caperucita en el escenario nuevo
     * @return escenario copiado
     */
    private static Escenario copiar(Escenario escenario, Posicion nuevaPosicionCaperucita) {
        Escenario nuevoEscenario = new Escenario();

        // Pasaje de atributos básicos.
        nuevoEscenario.matriz = Escenario.copiarMatriz(escenario.matriz);
        nuevoEscenario.cantidadDulces = escenario.cantidadDulces;
        nuevoEscenario.posicionInicialCaperucita = escenario.posicionInicialCaperucita;
        nuevoEscenario.posicionActualLobo = escenario.posicionActualLobo;
        nuevoEscenario.posicionesPosiblesLobo = new ArrayList<>(escenario.posicionesPosiblesLobo);
        nuevoEscenario.posicionesCampoFlores = new ArrayList<>(escenario.posicionesCampoFlores);
        nuevoEscenario.posicionesDulces = escenario.posicionesDulces;

        // Se insertan nuevamente los campos de flores por si Caperucita se posicionó en uno previamente
        for(Posicion posicion: escenario.posicionesCampoFlores) {
            nuevoEscenario.insertar('F', posicion);
        }

        // Se reemplaza la posición actual de Caperucita por una celda vacía.
        nuevoEscenario.limpiar(escenario.posicionActualCaperucita);
        // Se actualiza la posición actual de Caperucita.
        nuevoEscenario.posicionActualCaperucita = nuevaPosicionCaperucita;
        // Se actualiza la celda correspondiente a la nueva posición de Caperucita.
        nuevoEscenario.insertar('C', nuevaPosicionCaperucita);

        return nuevoEscenario;
    }

    /**
     * Retorna un escenario modificado, producido por un movimiento de caperucita.
     *
     * @param escenario               escenario actual a obtener modificado.
     * @param nuevaPosicionCaperucita posición de Caperucita luego de realizar el movimiento.
     * @param posicionesDulces        posiciones de los dulces recolectados en el movimiento.
     */
    public static Escenario obtenerEscenarioActualizado(Escenario escenario,
                                                        Posicion nuevaPosicionCaperucita,
                                                        List<Posicion> posicionesDulces,
                                                        Boolean esEtapaBusqueda) {
        // Obtiene el escenario base a copiar.
        Escenario nuevoEscenario = Escenario.copiar(escenario, nuevaPosicionCaperucita);

        // Elimina los dulces recolectados en el nuevo escenario.
        for (Posicion posicion : posicionesDulces) {
            char celda = nuevoEscenario.matriz[posicion.i][posicion.j];
            if (celda == 'D') nuevoEscenario.limpiar(posicion);
        }

        if (!esEtapaBusqueda) {
            nuevoEscenario.moverLobo();
        }

        return nuevoEscenario;
    }

    /**
     * Retorna un escenario modificado, producido cuando Caperucita pierde una vida.
     *
     * @param escenario       escenario original desde el que se parte para obtener el escenario nuevo.
     * @param esEtapaBusqueda si se ejecuta durante la búsqueda o la actualización real del agente.
     * @return escenario reiniciado (Caperucita en la posición inicial y dulces restaurados).
     */
    public static Escenario obtenerEscenarioReiniciado(Escenario escenario, Boolean esEtapaBusqueda) {
        // Obtiene el escenario base a copiar.
        Escenario nuevoEscenario = Escenario.copiar(escenario, escenario.posicionInicialCaperucita);

        // Restaura los dulces en el nuevo escenario.
        for (Posicion posicion : nuevoEscenario.posicionesDulces) {
            nuevoEscenario.insertar('D', posicion);
        }

        // Si se reinicia el escenario al de ejecutar el algoritmo de búsqueda,
        // Se elimina al lobo de su posición actual.
        if (esEtapaBusqueda) {
            nuevoEscenario.limpiar(escenario.posicionActualLobo);
        } else {
            nuevoEscenario.moverLobo();
        }

        return nuevoEscenario;
    }

    public static Boolean esCampoDeFlores(Escenario escenario, Posicion posicion) {
        return escenario.posicionesCampoFlores.stream().anyMatch(posicion::equals);
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
        nuevoEscenario.posicionesCampoFlores = new ArrayList<>(escenario.posicionesCampoFlores);
        nuevoEscenario.posicionActualCaperucita = escenario.posicionActualCaperucita;
        nuevoEscenario.cantidadDulces = escenario.cantidadDulces;
        nuevoEscenario.posicionActualLobo = escenario.posicionActualLobo.clone();

        return nuevoEscenario;
    }

    /**
     * Mueve al lobo dentro del escenario, tomando como siguiente posición una posición de la lista mezclada
     * aleatoriamente. No admite que el lobo se posicione sobre Caperucita.
     */
    private void moverLobo() {
        // Se elige la primer posición posible de la lista de posiciones mezclada.
        if (!this.posicionesPosiblesLobo.isEmpty()) {

            // Se reemplaza la posición actual del lobo por una celda vacía.
            this.limpiar(posicionActualLobo);

            // Se agrega la posición previa del lobo como una nueva posición posible.
            this.posicionesPosiblesLobo.add(this.posicionActualLobo.clone());

            // No se admite que el lobo se posiciones sobre Caperucita.
            Posicion nuevaPosicionLobo = this.posicionesPosiblesLobo.remove(0);
            while (nuevaPosicionLobo.equals(this.posicionActualCaperucita)) {
                nuevaPosicionLobo = this.posicionesPosiblesLobo.remove(0);
            }

            this.insertar('L', nuevaPosicionLobo);
            this.posicionActualLobo = nuevaPosicionLobo;
        }
    }

    /**
     * Inserta un elemento en el escenario
     *
     * @param elemento elemento a insertar.
     * @param posicion posición del escenario en la que se inserta el elemento.
     */
    private void insertar(char elemento, Posicion posicion) {
        matriz[posicion.i][posicion.j] = elemento;
    }

    /**
     * Elimina un elemento del escenario
     *
     * @param posicion posición a limpiar
     */
    private void limpiar(Posicion posicion) {
        matriz[posicion.i][posicion.j] = ' ';
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
                    posicionesCampoFlores.add(new Posicion(i, j));
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

    public Posicion getPosicionInicialCaperucita() {
        return posicionInicialCaperucita;
    }

    public void setPosicionInicialCaperucita(Posicion posicionInicialCaperucita) {
        this.posicionInicialCaperucita = posicionInicialCaperucita;
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
