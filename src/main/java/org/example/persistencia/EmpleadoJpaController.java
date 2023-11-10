package org.example.persistencia;

import org.example.logica.Empleado;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class EmpleadoJpaController {

    private EntityManagerFactory emf = null;

    public EmpleadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("empresaPU");
    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    //Crear un usuario según los datos enviados por argumento desde el main.
    public void create (Empleado empleado){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();

    }
    //Eliminar un usuario según un id enviado desde el main.
    public void destroy (Long id){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Empleado empleado = em.find(Empleado.class, id);
        em.remove(empleado);
        em.getTransaction().commit();

    }
    //Editar un usuario según un id enviado desde el main.
    public void edit(Empleado empleadoActualizado){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(empleadoActualizado);
        em.getTransaction().commit();

    }
    //Listar los empleados de la base de datos.
    public List<Empleado> findEmpleadosEntities(){
        EntityManager em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Empleado.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    //Hace una búsqueda de los empleados que tienen un cargo similar al introducido en la base de datos.
    public List<Empleado> findEmpleadosCargo(String cargoBuscado){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
        cq.where(cb.equal(cq.from(Empleado.class).get("cargo"), cargoBuscado));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }
    //Buscar un emplado con un id en concreto, válido para eliminar o editar.
    public Empleado findEmpleadoSimple(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Empleado.class, id);
    }
}
