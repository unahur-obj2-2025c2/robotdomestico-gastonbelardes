package ar.edu.unahur.obj2.command.Operaciones;
import ar.edu.unahur.obj2.Excepciones.BateriaInsuficienteException;
import ar.edu.unahur.obj2.RobotDomestico.Robot;

public interface Operable {

    void ejecutar(Robot robot) throws BateriaInsuficienteException;

    String descripcion();

}
