package entidades;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo",
		discriminatorType = DiscriminatorType.STRING)
public class Copia {

	public static final boolean DETERIORADO = true;
	public static final boolean COMO_NUEVO = false;
	
	@Id
	@GeneratedValue
	private int id_copia;
	private String editorial;
	private int anio_publica;

	@ManyToOne(cascade = {CascadeType.ALL})
	private Persona prestatario;

	// Constructores
	public Copia(){

	}

	public Copia(String editorial, int anio_publica) {
		this.editorial = editorial;
		this.anio_publica = anio_publica;
	}

	public int getId_copia() {
		return id_copia;
	}

	// Getters y Setters de Relaciones
	public Persona getPrestatario() {
		return prestatario;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getAnio_publica() {
		return anio_publica;
	}

	public void setAnio_publica(int anio_publica) {
		this.anio_publica = anio_publica;
	}

	public void addPrestatario(Persona pres) {
		if (this.prestatario != null)
			throw new RuntimeException("Esta copia ya está prestada");
		this.prestatario = pres;
		pres.getLibros().add(this);
	}

	public void removePrestatario() {
		if (this.prestatario == null)
			throw new RuntimeException("Esta copia no está prestada");
		this.prestatario.getLibros().remove(this);
		prestatario = null;
	}

	/*public Copia_Digital addCopiaDigital(String fich, String form,int tam){
		Copia_Digital cpd = new Copia_Digital(fich, form,tam);
		cpd.setOriginal(this);
		return cpd;
	}*/

	// toString y equals
	@Override
	public String toString() {
		String s = String.format("Copia [%d]", id_copia);
		s = s + (prestatario != null?
				" / prestado a "+prestatario.getApellidos()+" "+prestatario.getNombre() +
				" [" + prestatario.getDni() + "]"
				: "");
		return s;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copia other = (Copia) obj;
		if (id_copia != other.getId_copia())
			return false;
		return true;
	}
}
