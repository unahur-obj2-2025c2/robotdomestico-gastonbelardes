package ar.edu.unahur.obj2.command.Operaciones;
import ar.edu.unahur.obj2.Excepciones.BateriaInsuficienteException;
import ar.edu.unahur.obj2.Habitaciones.Habitacion;
import ar.edu.unahur.obj2.RobotDomestico.Robot;

public class LIGOFF implements Operable {
    private Habitacion habitacion;

    public LIGOFF(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public void ejecutar(Robot robot) throws BateriaInsuficienteException {
        double consumo = habitacion.estaLuzEncendida() ? 5.0 : 1.0;

        if (robot.getBateria() < consumo) {
            throw new BateriaInsuficienteException("No hay suficiente batería para ejecutar " + descripcion());
        }

        
        int duracion = habitacion.estaLuzEncendida() ? 90 : 25;

        robot.usarBateria(consumo);

        robot.registrarTarea(descripcion(), duracion);

        habitacion.apagarLuz();
    }

    @Override
    public String descripcion() {
        return "Apagar luz del robot";
    }

}
