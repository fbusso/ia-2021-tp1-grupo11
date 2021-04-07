package busqueda;

import dominio.Escenario;
import dominio.Posicion;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoEscenario extends EnvironmentState {

  private Escenario escenario;
  private Posicion posicionCaperucita;

  public EstadoEscenario() {}

  @Override
  public void initState() {
    this.escenario = new Escenario(9, 14);
    this.posicionCaperucita = new Posicion(0, 0);
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return null;
  }
}
