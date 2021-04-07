package dominio;

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
  
  public Integer getCantidadDulces() {
    return this.cantidadDulces;
  }
  
  public Boolean esCampoDeFlores(Posicion posicion) {
    return matriz[posicion.i][posicion.j] == 'F';
  }
}
