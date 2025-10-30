package ar.edu.unahur.obj2.RobotDomestico;


public class TareaEjecutada {
    private String descripcion;
    private int duracion;

    

    public TareaEjecutada(String descripcion, int duracion) {
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

}
