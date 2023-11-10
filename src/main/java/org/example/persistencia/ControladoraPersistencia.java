package org.example.persistencia;

import org.example.logica.Empleado;

import java.util.List;

public class ControladoraPersistencia {
    //Controladora de persistencia donde están todos los métodos del JPA y lo conecta con el Main.
    //Cada uno de los métodos corresponde con el CRUD del EmpleadoJpaController
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();

    public void crearEmpleado(Empleado empleado){
        empleadoJpa.create(empleado);
    }

    public void eliminarEmpleado(Long id){
        empleadoJpa.destroy(id);
    }

    public void editarEmpleado(Empleado empleadoActualizado){
        empleadoJpa.edit(empleadoActualizado);

    }

    public List<Empleado> listarEmpleados(){
        return empleadoJpa.findEmpleadosEntities();
    }

    public List<Empleado> listarEmpleadosCargo(String cargoBuscado){
        return empleadoJpa.findEmpleadosCargo(cargoBuscado);
    }

    public Empleado buscarEmpleado(Long id) {
        return empleadoJpa.findEmpleadoSimple(id);
    }

}
