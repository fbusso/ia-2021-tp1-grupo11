package auxiliar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class AuxiliarCsv {
    static final String PATH = "C:\\Users\\franc\\Documents\\test.txt";

    public static char[][] obtenerMatriz() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(PATH));
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

    public static void main(String[] args) throws IOException {
        char[][] matriz = obtenerMatriz();
        System.out.println(Arrays.deepToString(matriz));
    }

}
