package ar.edu.unahur.obj2.Invoker;
import java.util.List;

import ar.edu.unahur.obj2.Excepciones.BateriaInsuficienteException;
import ar.edu.unahur.obj2.RobotDomestico.Robot;
import ar.edu.unahur.obj2.command.Operaciones.Operable;

public class ProgramadorRobot {

      private List<Operable> tareas;

    public ProgramadorRobot(List<Operable> tareas) {
        this.tareas = tareas;
    }

    public void ejecutarTodas(Robot robot) throws BateriaInsuficienteException {
        for (Operable tarea : tareas) {
            tarea.ejecutar(robot);
        }
    }
}


