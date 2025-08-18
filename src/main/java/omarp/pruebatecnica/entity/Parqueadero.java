package omarp.pruebatecnica.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "parqueadero")
public class Parqueadero {
 
	
    
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    
    private String nombre;
    
    @Column(name = "capacidad_maxima", nullable = false)
    @Min(1)
    @NotNull
    private int capacidadMaxima;
    
    @Column(name = "costo_por_hora", nullable = false, scale = 2)
    @Min(0)
    @NotNull
    private int costoPorHora;
    
    @OneToMany(mappedBy = "parqueadero", cascade = CascadeType.ALL)
    private Set<VehiculoParqueado> vehiculosParqueados;
    
}
