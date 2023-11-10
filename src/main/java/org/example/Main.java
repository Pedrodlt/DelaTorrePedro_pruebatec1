package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Variables de la aplicación
        long idActualizar;
        String nombre;
        String apellido;
        String cargo;
        double salario;
        String fechaInicio;
        String nombreActualizado;
        String apellidoActualizado;
        String cargoActualizado;
        double salarioActualizado;
        String fechaInicioActualizado;
        String cargoBuscado;
        long idEliminar;
        boolean empleadoEditado = false;
        boolean empleadoEliminado = false;
        boolean cargoValido = false;

        //Llamada a la controladora y al scanner
        ControladoraPersistencia controladora = new ControladoraPersistencia();

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n********* Bienvenido a la BBDD de Softtek *********");

        //Menú de la aplicación. El usuario introduce la opción deseada.
        while(true){

            System.out.println("\n Elija una opción de las siguientes:");
            System.out.println("1. Agregar nuevo empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Actualizar empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Buscar empleados por cargo");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            //Primera opción, Crear un nuevo usuario con los datos introducidos.
            switch (opcion){
                case 1:
                    System.out.println("Ingrese el nombre del empleado: ");
                    nombre = scanner.nextLine();

                    System.out.println("Ingrese el apellido del empleado: ");
                    apellido = scanner.nextLine();

                    System.out.println("Ingrese el cargo del empleado: ");
                    cargo = scanner.nextLine();

                    System.out.println("Ingrese el salario del empleado: ");
                    salario = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Ingrese la fecha de inicio del empleado (YYYY-MM-DD): ");
                    fechaInicio = scanner.nextLine();
                    //Se comprueba que los datos no estén vacios, en caso contrario lanza mensaje de error.
                    if (!nombre.isEmpty() && !apellido.isEmpty() && !cargo.isEmpty() && salario>0 && !fechaInicio.isEmpty()) {
                        Empleado nuevoEmpleado = new Empleado();
                        nuevoEmpleado.setNombre(nombre);
                        nuevoEmpleado.setApellido(apellido);
                        nuevoEmpleado.setCargo(cargo);
                        nuevoEmpleado.setSalario(salario);
                        nuevoEmpleado.setFechaInicio(fechaInicio);

                        controladora.crearEmpleado(nuevoEmpleado);

                        System.out.println("\nEmpleado creado con éxito.");
                    } else {
                        System.out.println("\nError al crear el empleado. Verifique los datos proporcionados.");
                    }

                    break;
                //Segunda ocpión, Listado de empleados de la base de datos.
                case 2:
                    System.out.println("\n--------- Listado de empleados de Softtek ---------");
                    List<Empleado> empleados = controladora.listarEmpleados();
                    for (Empleado empleado : empleados) {
                        System.out.println("-Empleado con ID: "+empleado.getId()+" - Nombre: " +empleado.getNombre()
                                + " - Apellido: "+empleado.getApellido()+ " - Cargo: "+empleado.getCargo());
                    }

                    break;
                //Tercera opción, Editar un usuario según un ID ingresado. Si el ID no existe, lanza error y vuelve a pedir datos.
                case 3:
                    do {
                        System.out.print("Ingrese el ID del empleado que desea actualizar: ");
                        idActualizar = scanner.nextLong();
                        scanner.nextLine();

                        Empleado empleadoExistente = controladora.buscarEmpleado(idActualizar);

                        if (empleadoExistente == null) {
                            System.out.println("Empleado no encontrado. Ingrese un ID válido.");
                        } else {

                            Empleado empleadoActualizado = new Empleado();
                            empleadoActualizado.setId(idActualizar);

                            System.out.println("Ingrese el nuevo nombre del empleado: ");
                            nombreActualizado = scanner.nextLine();

                            System.out.println("Ingrese el nuevo apellido del empleado: ");
                            apellidoActualizado = scanner.nextLine();

                            System.out.println("Ingrese el nuevo cargo del empleado: ");
                            cargoActualizado = scanner.nextLine();

                            System.out.println("Ingrese el nuevo salario del empleado: ");
                            salarioActualizado = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.println("Ingrese la nueva fecha de inicio del empleado (YYYY-MM-DD): ");
                            fechaInicioActualizado = scanner.nextLine();
                            //Se comprueba que los datos no estén vacios, en caso contrario lanza mensaje de error.
                            if (!nombreActualizado.isEmpty() && !apellidoActualizado.isEmpty() && !cargoActualizado.isEmpty()
                                    && salarioActualizado>0 && !fechaInicioActualizado.isEmpty()) {

                                empleadoActualizado.setNombre(nombreActualizado);
                                empleadoActualizado.setApellido(apellidoActualizado);
                                empleadoActualizado.setCargo(cargoActualizado);
                                empleadoActualizado.setSalario(salarioActualizado);
                                empleadoActualizado.setFechaInicio(fechaInicioActualizado);

                                controladora.editarEmpleado(empleadoActualizado);
                                empleadoEditado=true;
                                System.out.println("\nEmpleado actualizado con éxito.");

                            } else {
                                System.out.println("\nError al actualizar el empleado. Verifique los datos proporcionados.");
                            }
                        }

                    }while(!empleadoEditado);

                    break;
                //Cuarta opción, eliminar un usuario según un ID introducido. En caso de no exisitir el ID vuelve a solicitar uno válido.
                case 4:
                    do{
                        System.out.print("Ingrese el ID del empleado que desea eliminar: ");
                        idEliminar = scanner.nextLong();

                        Empleado empleadoAEliminar = controladora.buscarEmpleado(idEliminar);

                        if(empleadoAEliminar==null){
                            System.out.println("El ID no existe, introduzca un ID válido.");
                        }else{
                            controladora.eliminarEmpleado(idEliminar);
                            empleadoEliminado = true;
                            System.out.println("\nEmpleado eliminado con éxito.");
                        }

                    }while (!empleadoEliminado);

                    break;
                //Quinta opción, hace una busqueda de los usuarios que tienen un determinado cargo. En caso de que el cargo no exista, lanza error.
                case 5:
                    do{
                        System.out.print("Ingrese el cargo para buscar empleados: ");
                        cargoBuscado = scanner.next();

                        if(controladora.listarEmpleadosCargo(cargoBuscado).isEmpty()){
                            System.out.println("El cargo introducido no existe, introduzca un cargo válido.");
                        }else{
                            List<Empleado> empleadosPorCargo = controladora.listarEmpleadosCargo(cargoBuscado);
                            System.out.println("\n--------- Listado de empleados por Cargo ---------");
                            for (Empleado empleado : empleadosPorCargo) {
                                System.out.println("-Empleado con ID: "+empleado.getId()+" - Nombre: " +empleado.getNombre()
                                        + " - Apellido: "+empleado.getApellido()+ " - Cargo: "+empleado.getCargo());
                            }
                            cargoValido=true;
                        }

                    }while(!cargoValido);

                    break;
                //Sexta opción, cierra la aplicación.
                case 6:
                    System.out.println("Saliendo de la aplicación.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción correcta del menú.");
                    break;

            }
        }
    }
}