package principal;

import entidades.Copia;
import entidades.Libro;
import entidades.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Iterator;
import java.util.List;

public class ListPerPrestaIterador_v2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/biblioteca");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l",Libro.class).getResultList();
        for (Persona persona : personas){
            System.out.println(personas);
            for (Copia copia : persona.getLibros()){
                System.out.println(" -[copia "+copia.getId_copia() +"] Titulo: ");
                Iterator<Libro> i = libros.iterator();
                Libro aux=null;
                while (i.hasNext() && !(aux=i.next()).getCopias().contains(copia));
                if (aux!=null && aux.getCopias().contains(copia))
                    System.out.println("\"" +aux.getTitulo()+ "\"");
                else
                    throw new RuntimeException("La copia prestada no se corresponde con nigún libro de la BD");
            }
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
