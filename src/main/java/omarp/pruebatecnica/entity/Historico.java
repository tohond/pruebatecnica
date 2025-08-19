package omarp.pruebatecnica.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "historico")
public class Historico {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "vehiculo_id", nullable = false)
	    @NotNull
	    private Vehiculo vehiculo;
	    
	    @ManyToOne
	    @JoinColumn(name = "parqueadero_id", nullable = false)
	    @NotNull
	    private Parqueadero parqueadero;
	    
	    @Column(name = "fecha_ingreso", nullable = false)
	    @NotNull
	    private LocalDateTime fechaIngreso;
	    
	    @Column(name = "fecha_salida", nullable = false)
	    @NotNull
	    private LocalDateTime fechaSalida;
	    
	    
	    
	    @Column(name = "costoxhora_momento", nullable = false)
	    @NotNull
	    private int costoxhoraMomento;
	    
	    /*@PrePersist
	    @PreUpdate
	    protected void calcularCostoYHoras() {
	        if (fechaIngreso != null && fechaSalida != null && parqueadero != null) {
	            // Calcular horas parqueado (redondeando hacia arriba)
	            long minutos = ChronoUnit.MINUTES.between(fechaIngreso, fechaSalida);
	            horasParqueado = (minutos + 59) / 60; // Redondeo hacia arriba
	            
	            // Calcular costo total
	            costoTotal = parqueadero.getCostoPorHora().multiply(BigDecimal.valueOf(horasParqueado));
	        }
	    }*/
	    public Historico(VehiculoParqueado vehiculoParqueado, LocalDateTime fechaSalida) {
	        this.vehiculo = vehiculoParqueado.getVehiculo();
	        this.parqueadero = vehiculoParqueado.getParqueadero();
	        this.fechaIngreso = vehiculoParqueado.getFechaIngreso();
	        this.fechaSalida = fechaSalida;
	        this.costoxhoraMomento = vehiculoParqueado.getParqueadero().getCostoPorHora();
	    }
	    
}
