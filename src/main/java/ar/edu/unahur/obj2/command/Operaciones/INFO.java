package ar.edu.unahur.obj2.command.Operaciones;
import ar.edu.unahur.obj2.RobotDomestico.Robot;
import ar.edu.unahur.obj2.RobotDomestico.TareaEjecutada;

public class INFO implements Operable {

    @Override
    public void ejecutar(Robot robot) {
        int suma = 0;
        int contador = 0;

        for (TareaEjecutada t : robot.getHistorial()) {
            if (!t.getDescripcion().equals("INFO")) {  // ignoramos INFO
                suma += t.getDuracion();
                contador++;
            }
        }

        if (contador > 0) {
            double promedio = (double) suma / contador;
            System.out.println("Promedio de duración de tareas: " + promedio + " segundos");
        } else {
            System.out.println("No se han ejecutado tareas para calcular el promedio.");
        }
    }

    @Override
    public String descripcion() {
        return "INFO";
    }
}
