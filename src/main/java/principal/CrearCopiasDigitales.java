package principal;

import entidades.Copia;
import entidades.Copia_Digital;
import entidades.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CrearCopiasDigitales {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("DELETE FROM Copia_Digital ");
        query.executeUpdate();

        List<Libro> libros = em.createQuery("SELECT l FROM Libro l",Libro.class).getResultList();
        for (Libro lib : libros){
            String tit = lib.getTitulo().replace(' ', '_');
            Copia_Digital copia_digital = new Copia_Digital("EDIT",2023,tit+".pdf","PDF",1024);
            lib.getCopias().add(copia_digital);
            em.merge(lib);
            //em.persist(cp.addCopiaDigital(tit+".pdf", "PDF"));
        }
        em.getTransaction().commit();

        Query query2 = em.createQuery("SELECT cd FROM Copia_Digital cd",Copia_Digital.class);
        List<Copia_Digital> copiasDigitales = query2.getResultList();
        for (Copia_Digital cd : copiasDigitales){
            System.out.println("\t"+cd);
        }

        em.close();
        emf.close();
    }
}
