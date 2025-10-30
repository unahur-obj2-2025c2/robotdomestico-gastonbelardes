package ar.edu.unahur.obj2.RobotDomestico;
import java.util.ArrayList;

public class Robot {
    private double bateria;
    private ArrayList<TareaEjecutada> historial;
   
    
    

    public Robot(){
        this.bateria = 100.0;
        this.historial = new ArrayList<TareaEjecutada>();
        
    }

    public void registrarTarea(String descripcion, int duracion) {
        TareaEjecutada t = new TareaEjecutada(descripcion, duracion);
        historial.add(t);
    }

    public ArrayList<TareaEjecutada> getHistorial() {
        return historial;
    }

    public void cargarBateria(double incremento) {
        this.bateria += incremento;
        if (this.bateria > 100) this.bateria = 100;
    }

    public void usarBateria(double consumo) {
        this.bateria -= consumo;
        if (this.bateria < 0) this.bateria = 0;
    }

    public double getBateria() {
        return this.bateria;
    }



  
}

    

