package omarp.pruebatecnica.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter 
@Getter
@Entity
@Table(name = "parqueadero_socio")
public class Parqueadero_Socio {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "socio_id", nullable = false)
	private User socio;
	
	@ManyToOne
	@JoinColumn(name = "parqueadero_id", nullable = false)
	private Parqueadero parqueadero;
	
}
