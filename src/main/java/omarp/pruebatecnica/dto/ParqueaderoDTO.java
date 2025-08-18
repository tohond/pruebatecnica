package omarp.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParqueaderoDTO {
    
    private Long id;
    
    private String nombre;
    
    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser mayor a 0")
    private int capacidadMaxima;
    
    @NotNull(message = "El costo por hora es obligatorio")
    @Positive(message = "El costo por hora debe ser mayor a 0")
    private int costoPorHora;
    
   
    
    
}

