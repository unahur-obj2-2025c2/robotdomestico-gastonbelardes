package ar.edu.unahur.obj2.command.Operaciones;
import ar.edu.unahur.obj2.Excepciones.BateriaInsuficienteException;
import ar.edu.unahur.obj2.Habitaciones.Habitacion;
import ar.edu.unahur.obj2.RobotDomestico.Robot;

public class LIGON implements Operable {
    private Habitacion habitacion;

    public LIGON(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public void ejecutar(Robot robot) throws BateriaInsuficienteException {
        double consumo = habitacion.estaLuzEncendida() ? 1.0 : 5.0;

        if (robot.getBateria() < consumo) {
        throw new BateriaInsuficienteException("No hay suficiente batería para ejecutar " + descripcion());
    }

        
        int duracion = habitacion.estaLuzEncendida() ? 25 : 90;

        robot.usarBateria(consumo);

        robot.registrarTarea(descripcion(), duracion);

        habitacion.encenderLuz();
    }

    @Override
    public String descripcion() {
        return "Encender la luz de la habitación";
    }

   
}
