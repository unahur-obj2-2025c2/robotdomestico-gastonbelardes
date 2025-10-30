package ar.edu.unahur.obj2.Habitaciones;



public class Habitacion {
    private boolean luzEncendida;
    private Integer metrosCuadrados;

    public Habitacion(double metrosCuadrados) {
        this.luzEncendida = false;
        this.metrosCuadrados = (int) metrosCuadrados;
    }

    public boolean estaLuzEncendida() {
        return luzEncendida;
    }

    public void encenderLuz() {
        this.luzEncendida = true;
    }

    public void apagarLuz() {
        this.luzEncendida = false;
    }

    public double getMetrosCuadrados() {
        return this.metrosCuadrados;
    }
}

