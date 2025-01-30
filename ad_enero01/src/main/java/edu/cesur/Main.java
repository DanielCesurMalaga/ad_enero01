package edu.cesur;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        // Crear EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personasPU");
        EntityManager em = emf.createEntityManager();

        // Iniciar una transacción
        em.getTransaction().begin();

        // Crear una nueva persona y persistirla
        Persona persona1 = new Persona("Daniel Fernández", 100);
        persona1.insertarVisita(new Date(2025, 1, 30), 0);
        persona1.insertarVisita(new Date(2025, 1, 31), 1);

        em.persist(persona1);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Consultar todas las personas en la base de datos
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        for (Persona persona : personas) {
            System.out.println(persona);
        }

        // Cerrar EntityManager y EntityManagerFactory
        em.close();
        emf.close();

    }
}