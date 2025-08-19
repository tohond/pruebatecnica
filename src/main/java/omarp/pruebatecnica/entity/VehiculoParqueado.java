package omarp.pruebatecnica.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "vehiculo_parqueado")
public class VehiculoParqueado {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehiculo_id", nullable = false, unique = true)
    @NotNull
    private Vehiculo vehiculo;
    
    @ManyToOne
    @JoinColumn(name = "parqueadero_id", nullable = false)
    @NotNull
    private Parqueadero parqueadero;
    
    @Column(name = "fecha_ingreso", nullable = false)
    @NotNull
    private LocalDateTime fechaIngreso;
    
    @PrePersist
    protected void onCreate() {
        if (fechaIngreso == null) {
            fechaIngreso = LocalDateTime.now();
        }
    }
    
   
    public VehiculoParqueado() {}
    
    public VehiculoParqueado(Vehiculo vehiculo, Parqueadero parqueadero) {
        this.vehiculo = vehiculo;
        this.parqueadero = parqueadero;
        this.fechaIngreso = LocalDateTime.now();
    }
    
	
}
