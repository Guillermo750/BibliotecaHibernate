package entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fisica")
public class Copia_Fisica extends Copia{

    private Boolean deteriorado;

    public Copia_Fisica(String editorial, int ano_pub, Boolean deteriorado) {
        super(editorial,ano_pub);
        this.deteriorado = deteriorado;
    }

    public Boolean isDeteriorado() {
        return deteriorado;
    }

    public void setDeteriorado(Boolean deteriorado) {
        this.deteriorado = deteriorado;
    }
}
