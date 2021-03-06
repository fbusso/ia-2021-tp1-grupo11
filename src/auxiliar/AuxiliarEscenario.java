package auxiliar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class AuxiliarEscenario {
    private static final String ESCENARIO_EJEMPLO_1 = "resources\\escenario_ejemplo_1.txt";
    private static final String ESCENARIO_EJEMPLO_2 = "resources\\escenario_ejemplo_2.txt";
    private static final String ESCENARIO_EJEMPLO_3 = "resources\\escenario_ejemplo_3.txt";
    private static final String ESCENARIO_MOVER_ABAJO_EXITO = "resources\\escenario_mover_abajo_exito.txt";
    private static final String ESCENARIO_PRUEBAS_EN_PROFUNIDAD = "resources\\escenario_pruebas_en_profundidad.txt";
    private static final String ESCENARIO_MOVER_ABAJO_Y_ARRIBA = "resources\\escenario_mover_abajo_y_arriba.txt";
    private static final String ESCENARIO_MOVER_ARRIBA_EXITO = "resources\\escenario_mover_arriba_exito.txt";
    private static final String ESCENARIO_MOVER_ARRIBA_MOVER_DERECHA_EXITO = "resources\\escenario_mover_arriba_mover_derecha_exito.txt";
    private static final String ESCENARIO_MOVER_ARRIBA_MOVER_IZQUIERDA_EXITO = "resources\\escenario_mover_arriba_mover_izquierda_exito.txt";
    private static final String ESCENARIO_PERSONALIZADO = "resources\\escenario_personalizado.txt";
    private static final String ESCENARIO_SIN_IR_ABAJO = "resources\\escenario_sin_ir_abajo.txt";
    private static final String ESCENARIO_TODOS_LOS_MOVIMIENTOS = "resources\\escenario_todos_los_movimientos.txt";

    public static char[][] obtenerMatriz() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(ESCENARIO_EJEMPLO_1));
            int i = 0, j = 0;
            char[][] matriz = new char[9][14];

            String linea;
            while ((linea = csvReader.readLine()) != null) {
                String[] caracteresEnLinea = linea.split(",");
                for (String c : caracteresEnLinea) {
                    matriz[i][j] = c.charAt(0);
                    j++;
                }
                j = 0;
                i++;

            }
            csvReader.close();
            return matriz;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        char[][] matriz = obtenerMatriz();
        System.out.println(Arrays.deepToString(matriz));
    }

}
