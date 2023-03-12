package entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Autor {
	@Id
	@GeneratedValue
	private int id_autor;
	@Column(unique = true)
	private String nombre;

	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "autores")
	private List<Libro> obras = new ArrayList<Libro>();

	// Constructores

	public Autor() {
	}

	public Autor(String n) {
		nombre = n;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public int getId_autor() {
		return id_autor;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Getters y Setters de Relaciones

	public List<Libro> getObras() {
		return obras;
	}

	// toString y equals
	@Override
	public String toString() {
	 	Collection<Libro> aux = getObras();
	 	String obras = "";
	 	if(aux.size()!=0) {
		 	for (Libro libro : aux) {
				obras+=libro.getTitulo()+", ";
			}
		 	obras = obras.substring(0, obras.length()-2);
	 	}
		return String.format("[%d]%s, autor de %s", id_autor, nombre,obras);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor otro = (Autor) obj;
		if (id_autor != otro.getId_autor())
			return false;
		return true;
	}
}
