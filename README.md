# DelaTorrePedro_pruebatec1

Desarrollo de una Aplicación de Gestión de Empleados 
El objetivo de esta prueba es evaluar tus conocimientos en Java, incluyendo sintaxis, estructuras repetitivas, estructuras selectivas, manejo de colecciones y operaciones CRUD (Crear, Leer, Actualizar y Borrar) utilizando JPA (Java Persistence API) para interactuar con una base de datos.

Pasos para ejecutar la aplicación
- Clonar el repositorio.
- Ejecturar en un IDE preferido para Java.
- Será necesario tener una BBDD con el nombre "empresa" en un gestor de Base de datos, en este caso MySQL.
- URL: jdbc:mysql://localhost:3306/empresa?serverTimezone=UTC
- USER: Root
- PASSWORD: 898989
- En caso de no utilizar estas credenciales, se deberá modificar en el archivo persistence.xml y actualizar los datos.
- Una vez codificada la Base de datos, ejecutar la aplicación desde el main.
  
Uso de la aplicación
- Una vez ejecutada la aplicación, elegir una de las opciones del menú según las indicaciones.
- Se podrá Crear un nuevo usuario, Listar los usuarios existentes, Editar un usuario existente, Eliminar un usuario, Buscar un usuario por cargo y salir de la aplicación, actualizandose automáticamente la Base de datos
- En caso de introducir un valor en blanco a la hora de crear o editar un usuario, saltará un error y habrá que elegir nueva opción.
- En caso de introducir un ID para eliminar o editar que no exista, nos volverá a solicitar el ID correcto.
- En caso de introducir un Cargo que no exista, nos volverá a solicitar que ingresemos el cargo correcto.
