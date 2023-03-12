package principal;

import entidades.*;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static entidades.Copia.COMO_NUEVO;
import static entidades.Copia.DETERIORADO;

public class Datos {
	public static void crear(EntityManager em) {
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Copia").executeUpdate();

		em.createQuery("DELETE FROM Copia_Digital").executeUpdate();
		em.createQuery("DELETE FROM Copia_Fisica").executeUpdate();
		em.createQuery("DELETE FROM Libro").executeUpdate();
		//em.createQuery("DELETE FROM Copia").executeUpdate();
		em.createQuery("DELETE FROM Autor").executeUpdate();
		em.createQuery("DELETE FROM Persona").executeUpdate();

//		Autor autor1 = new Autor("Fernando");  // Hace saltar la restriccion unique del nombre... cuando se haga persistente en la BD
		Autor autor1 = new Autor("Ana");
		Autor autor2 = new Autor("Fernando");
		Autor autor3 = new Autor("Ramón");
		Autor autor4 = new Autor("Cajal");
		Libro lazarillo = new Libro("000000", "Popular", "La Vida de Lazarillo de Tormes", 1554);
		Libro cerebro = new Libro("222222", "Científica", "Estudio del cerebro humano", 1890,
				Arrays.asList(autor3, autor4, autor1));
		Libro programacion = new Libro("999999", "IES Domingo Perez Minik", "Introducción a la Programación", 2020,
				autor2);

		Copia_Digital laz1_dig = new Copia_Digital("popular",2023,"lazarillo","pdf",1024);
		Copia_Digital laz2_dig = new Copia_Digital("popular",2023,"LazDeTor","pdf",2048);
		Copia_Digital prog1_dig = new Copia_Digital("informatica",2022,"PRO","pdf",512);
		Copia_Digital prog2_dig = new Copia_Digital("informatica",2022,"progarmacion","txt",1024);
		Copia_Digital cer1_dig = new Copia_Digital("cientifica",2021,"Cerebro_RyC","pdf",512);
		Copia_Digital cer2_dig = new Copia_Digital("cientifica",2021,"Ramon-Cajal-Cerebro", "prc",1024);

		Copia_Fisica laz1_fis = new Copia_Fisica("cient", 2020,false);
		Copia_Fisica prog1_fis = new Copia_Fisica("info", 2022,true);
		Copia_Fisica prog2_fis = new Copia_Fisica("info", 2022,false);
		Copia_Fisica cer1_fis = new Copia_Fisica("cient", 2021,true);

		lazarillo.getCopias().add(laz1_dig);
		lazarillo.getCopias().add(laz2_dig);
		lazarillo.getCopias().add(laz1_fis);
		programacion.getCopias().add(prog1_dig);
		programacion.getCopias().add(prog2_dig);
		programacion.getCopias().add(prog1_fis);
		programacion.getCopias().add(prog2_fis);
		cerebro.getCopias().add(cer1_dig);
		cerebro.getCopias().add(cer2_dig);
		cerebro.getCopias().add(cer1_fis);

		Persona p1 = new Persona("54063242V", "Eduardo", "Guerra Rodríguez", "C/ El Edén, 10", "+34 618295409",
				"eduardo.guerra.rguez@gmail.com");
		Persona p2 = new Persona("63524172L", "Paco", "Porras Padilla", "C/ Sin Nombre, 7", "+34 623121234",
				"papopa@gmail.com");
		Persona p3 = new Persona("81920372K", "Francisco", "Fernández Fariña", "C/ Con Nombre, 12", "+34 697386221",
				"Fra_Fer_Far@gmail.com");

		lazarillo.getCopias().get(0).addPrestatario(p3);
		programacion.getCopias().get(0).addPrestatario(p3);
		cerebro.getCopias().get(1).addPrestatario(p3);
		programacion.getCopias().get(1).addPrestatario(p1);
		cerebro.getCopias().get(0).addPrestatario(p1);

		em.persist(lazarillo);
		em.persist(cerebro);
		em.persist(programacion);

		em.persist(p1); // Ya está persistido
		em.persist(p2);
		em.persist(p3); // Ya está persistido

		System.out.println(autor2);
		em.getTransaction().commit();

		System.out.println(autor2);
		System.out.println(programacion);
		for (Copia cp : programacion.getCopias()) {
			System.out.println("\t" + cp);
		}
	}

	public static void mostrar(EntityManager em) {
		List<Libro> libros = em.createQuery("SELECT lib FROM Libro lib", Libro.class).getResultList();
		for (Libro l : libros) {
			System.out.println(l);
			for (Copia cp : l.getCopias()) {
				System.out.println("\t" + cp);
			}
		}
		System.out.println("\n***********************\n");
		System.out.println("Socios y prestamos actuales");
		List<Persona> personas = em.createQuery("SELECT per FROM Persona per", Persona.class).getResultList();
		for (Persona p : personas) {
			System.out.println();
			System.out.println(p);
			for (Copia cp : p.getLibros()) {
				System.out.println("\t" + cp);
			}
		}
	}
}
