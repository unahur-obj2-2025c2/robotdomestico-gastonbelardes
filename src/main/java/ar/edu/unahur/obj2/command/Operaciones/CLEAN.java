package ar.edu.unahur.obj2.command.Operaciones;
import ar.edu.unahur.obj2.Excepciones.BateriaInsuficienteException;
import ar.edu.unahur.obj2.Habitaciones.Habitacion;
import ar.edu.unahur.obj2.RobotDomestico.Robot;

public class CLEAN implements Operable {
    private Habitacion habitacion;


    public CLEAN(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public void ejecutar(Robot robot) throws BateriaInsuficienteException {
        double consumo = 5.0 * habitacion.getMetrosCuadrados();

        double metros = habitacion.getMetrosCuadrados();

        if (robot.getBateria() < consumo) {
            throw new BateriaInsuficienteException("No hay suficiente batería para ejecutar " + descripcion());
        }

        Integer duracion = (int) (180 * habitacion.getMetrosCuadrados());

        robot.usarBateria(consumo);

        robot.registrarTarea(descripcion(), duracion);
    }

    @Override
    public String descripcion() {
        return "Limpiar la habitación";
    }

}
