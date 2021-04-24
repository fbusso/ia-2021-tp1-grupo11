package acciones;

import auxiliar.Movimiento;
import busqueda.EstadoAmbiente;
import busqueda.EstadoCaperucita;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public interface ActualizarEstado {

    SearchBasedAgentState obtenerEstadoActualizado(Movimiento movimiento, EstadoCaperucita estadoAgente);

    EnvironmentState obtenerEstadoAcualizado(Movimiento movimiento, EstadoAmbiente estadoAmbiente, EstadoCaperucita estadoAgente);
}
