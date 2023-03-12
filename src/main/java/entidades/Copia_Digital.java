package entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("digital")
public class Copia_Digital extends Copia{
	private String nombre_fichero;
	private String formato;
	private Integer tamanio;

	
	// Constructores
	public Copia_Digital() {
	}

	public Copia_Digital(String fichero) {
		nombre_fichero = fichero;
		formato = "";
	}
	
	public Copia_Digital(String editorial, int ano_publicacion, String fichero, String formato,Integer tamanio) {
		super(editorial,ano_publicacion);
		this.nombre_fichero = fichero;
		this.formato = formato;
		this.tamanio = tamanio;
	}



	// Getters y Setters
	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNombre_fichero() {
		return nombre_fichero;
	}

	public Integer getTamanio() {
		return tamanio;
	}

	public void setTamanio(Integer tamanio) {
		this.tamanio = tamanio;
	}

	// toString y equals
	@Override
	public String toString() {
		return "--> Copia digital: "+nombre_fichero+" - "+formato+" - "+tamanio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copia_Digital other = (Copia_Digital) obj;
		if (!nombre_fichero.equals(other.getNombre_fichero()))
			return false;
		return true;
	}
}
