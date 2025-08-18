package omarp.pruebatecnica.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehiculo")
public class Vehiculo {
	@Id
	public final String placa;
	public String modelo;
	public String marca;
	
    
    // Relación uno a muchos con Historico
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private Set<Historico> historicos;
	
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vehiculo))
            return false;
        Vehiculo other = (Vehiculo)o;
        boolean equal = (this.getPlaca() == null && other.getPlaca() == null)
          || (this.getPlaca() != null && this.getPlaca().equals(other.getPlaca()));
        return equal;
    }
}
