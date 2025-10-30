package ar.edu.unahur.obj2.command.Operaciones;

import ar.edu.unahur.obj2.RobotDomestico.Robot;

public class CARGE implements Operable {

    private Integer minutos;

    public CARGE(Integer minutos) {
        this.minutos = minutos;
    }

    @Override
    public void ejecutar(Robot robot) {
        double bateriaAntes = robot.getBateria();

        // Calculamos el incremento según los minutos
        double incremento = minutos * 0.8;

        // Ajustamos para que la batería no pase de 100%
        if (bateriaAntes + incremento > 100.0) {
            incremento = 100.0 - bateriaAntes;
        }

        // Cargamos la batería
        robot.cargarBateria(incremento);

        // Calculamos la duración real de la tarea en segundos
        double duracionSegundos = (incremento / 0.8) * 60;
        int duracion = (int) duracionSegundos;

        // Registramos la tarea en el historial
        robot.registrarTarea(descripcion(), duracion);
    }

    @Override
    public String descripcion() {
        return "Cargar batería del robot";
    }
}

