package principal;

import entidades.Copia;
import entidades.Libro;
import entidades.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ListadoPersonaPrestamo {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/biblioteca");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Persona> pr = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l",Libro.class).getResultList();
        for (Persona persona : pr){
            List<Copia> prestamo = persona.getLibros();
            for (Copia copia : prestamo){
                int j=0;
                while (j < libros.size() && libros.get(j).getCopias().contains(copia)){
                    j++;
                }
                if (j < libros.size()){
                    System.out.println("La Persona "+persona+" tiene como préstamo los libros: "+libros.get(j).getTitulo());
                }else {
                    throw new RuntimeException("Dicha copia no corresponde a ningún libro");
                }
            }
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
