package auxiliar;

import dominio.Posicion;

import java.util.ArrayList;
import java.util.List;

public class Movimiento {
    private Integer cantidadDulcesEnCamino;
    private Boolean loboEnCamino;
    private Posicion posicionFinal;
    private List<Posicion> posicionesDulces;

    public Movimiento(Posicion posicion,
                      char[][] matriz,
                      Avanzar avanzar,
                      Retroceder retroceder,
                      EvaluarPosicion evaluarPosicion) {

        this.posicionesDulces = new ArrayList<>();
        this.loboEnCamino = false;

        Posicion nuevaPosicion = posicion.clone();
        avanzar.ejecutar(nuevaPosicion);

        while (evaluarPosicion.ejecutar(nuevaPosicion, matriz)) {
            if (matriz[nuevaPosicion.i][nuevaPosicion.j] == 'D') {
                this.posicionesDulces.add(nuevaPosicion.clone());
            } else if (matriz[nuevaPosicion.i][nuevaPosicion.j] == 'L') {
                this.loboEnCamino = true;
            }
            avanzar.ejecutar(nuevaPosicion);
        }

        retroceder.ejecutar(nuevaPosicion);
        this.posicionFinal = nuevaPosicion.clone();
        this.cantidadDulcesEnCamino = posicionesDulces.size();
    }

    public List<Posicion> getPosicionesDulces() {
        return posicionesDulces;
    }

    public void setPosicionesDulces(List<Posicion> posicionesDulces) {
        this.posicionesDulces = posicionesDulces;
    }

    public Integer getCantidadDulcesEnCamino() {
        return cantidadDulcesEnCamino;
    }

    public void setCantidadDulcesEnCamino(Integer cantidadDulcesEnCamino) {
        this.cantidadDulcesEnCamino = cantidadDulcesEnCamino;
    }

    public Boolean getLoboEnCamino() {
        return loboEnCamino;
    }

    public void setLoboEnCamino(Boolean loboEnCamino) {
        this.loboEnCamino = loboEnCamino;
    }

    public Posicion getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(Posicion posicionFinal) {
        this.posicionFinal = posicionFinal;
    }
}
