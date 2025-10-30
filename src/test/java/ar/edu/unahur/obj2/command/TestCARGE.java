package ar.edu.unahur.obj2.command;

import ar.edu.unahur.obj2.RobotDomestico.*;
import ar.edu.unahur.obj2.command.Operaciones.*;
import ar.edu.unahur.obj2.Excepciones.BateriaInsuficienteException;
import ar.edu.unahur.obj2.Habitaciones.Habitacion;
import ar.edu.unahur.obj2.Invoker.ProgramadorRobot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class RobotTest {

    @Test
    void testCARGEIncrementaBateria() throws BateriaInsuficienteException {
    Robot robot = new Robot();
    robot.usarBateria(50); // Baja a 50%

    CARGE carga = new CARGE(10); // 10 minutos
    carga.ejecutar(robot);

    // Verificamos que la batería se incrementó correctamente
    assertEquals(58.0, robot.getBateria(), 0.01);
}


    @Test
    void testLIGONConsumeBateria() throws BateriaInsuficienteException {
        Habitacion hab = new Habitacion(10);
        hab.encenderLuz(); // luz ya encendida
        Robot robot = new Robot();

        LIGON ligon = new LIGON(hab);
        ligon.ejecutar(robot);

        // consumo = 1% porque luz ya estaba encendida
        assertEquals(99.0, robot.getBateria(), 0.01);

        // luz debe seguir encendida
        assertTrue(hab.estaLuzEncendida());
    }

    @Test
    void testLIGOFFConsumeBateria() throws BateriaInsuficienteException {
        Habitacion hab = new Habitacion(10);
        hab.encenderLuz(); // luz encendida
        Robot robot = new Robot();

        LIGOFF ligoff = new LIGOFF(hab);
        ligoff.ejecutar(robot);

        // consumo = 5% porque luz estaba encendida
        assertEquals(95.0, robot.getBateria(), 0.01);

        // luz debe quedar apagada
        assertFalse(hab.estaLuzEncendida());
    }

    @Test
    void testCLEANConsumeBateria() throws BateriaInsuficienteException {
        Habitacion hab = new Habitacion(10); // 10 m²
        Robot robot = new Robot();

        CLEAN clean = new CLEAN(hab);
        clean.ejecutar(robot);

        // 100 - 10*5 = 50
        assertEquals(50.0, robot.getBateria(), 0.01);

        // duración = 10*180 = 1800 segundos
        assertEquals(1800, robot.getHistorial().get(0).getDuracion());
        assertEquals("Limpiar la habitación", robot.getHistorial().get(0).getDescripcion());
    }

    @Test
    void testINFOCalculaPromedio() throws BateriaInsuficienteException {
        Robot robot = new Robot();

        // Agregamos tareas manuales
        robot.registrarTarea("Tarea1", 120);
        robot.registrarTarea("Tarea2", 180);

        INFO info = new INFO();
        info.ejecutar(robot); // solo imprime promedio

        // calculamos manualmente
        int suma = 0;
        int contador = 0;
        for (TareaEjecutada t : robot.getHistorial()) {
            if (!t.getDescripcion().equals("INFO")) {
                suma += t.getDuracion();
                contador++;
            }
        }

        double promedio = (double) suma / contador;
        assertEquals(150.0, promedio, 0.01);
    }

    @Test
    void testProgramadorRobotEjecutaTodas() throws BateriaInsuficienteException {
        Habitacion hab = new Habitacion(5); // 5 m²
        Robot robot = new Robot();

        ProgramadorRobot programador = new ProgramadorRobot(
            List.of(
                new CARGE(5),    // sube batería 5*0.8 = 4%
                new LIGON(hab),  // luz apagada, consume 5%
                new CLEAN(hab)   // 5*5=25%
            )
        );

        programador.ejecutarTodas(robot);

        // Batería inicial 100 -> CARGE sube 0 -> LIGON 5% -> CLEAN 25%
        double esperado = 100.0 - 5.0 - 25.0; 
        assertEquals(70.0, robot.getBateria(), 0.01);

        // Historial debe tener 3 tareas
        assertEquals(3, robot.getHistorial().size());
    }
}
