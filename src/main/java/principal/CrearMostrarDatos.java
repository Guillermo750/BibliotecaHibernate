package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.logging.Level;

public class CrearMostrarDatos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		System.out.print("Desea eliminar los datos de anteriores ocasiones (S/N)? ");
		boolean mantenerDatos = Character.toLowerCase(sc.nextLine().trim().charAt(0)) == 'n';

		String uPersistencia = "biblioteca"+ (mantenerDatos?"":"_reseteada");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(uPersistencia);
		EntityManager em = emf.createEntityManager();

		if (!mantenerDatos) {
			Datos.crear(em);
		} else {
			Datos.mostrar(em);
		}
		em.close();
		emf.close();
		sc.close();
	}
}