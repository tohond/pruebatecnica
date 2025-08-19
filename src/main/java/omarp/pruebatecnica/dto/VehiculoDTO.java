package omarp.pruebatecnica.dto;

import lombok.Data;

@Data
public class VehiculoDTO {
	
	private String placa;
	private String modelo; // Puede ser "CARRO", "MOTO" o "BICICLETA"
	private String marca;
	private String nacionalidad; // ID del parqueadero al que pertenece el vehículo

	public VehiculoDTO() {
	}


}
