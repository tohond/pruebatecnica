package omarp.pruebatecnica.service;


import lombok.RequiredArgsConstructor;
import omarp.pruebatecnica.dto.ParqueaderoDTO;
import omarp.pruebatecnica.entity.Parqueadero;
import omarp.pruebatecnica.repository.ParqueaderoRepository;
import omarp.pruebatecnica.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ParqueaderoService {
    
    private final ParqueaderoRepository parqueaderoRepository;
    private final UserRepository usuarioRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    
    public ParqueaderoDTO crearParqueadero(ParqueaderoDTO requestDTO) {
        // Validar que el socio existe y tiene rol SOCIO
        
        
        Parqueadero parqueadero = modelMapper.map(requestDTO, Parqueadero.class);
        
        Parqueadero savedParqueadero = parqueaderoRepository.save(parqueadero);
        return modelMapper.map(savedParqueadero, ParqueaderoDTO.class);
    }
    
    @Transactional(readOnly = true)
    public List<ParqueaderoDTO> obtenerTodosLosParqueaderos() {
        return parqueaderoRepository.findAll().stream()
				.map(parqueadero -> modelMapper.map(parqueadero, ParqueaderoDTO.class))
				.collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public ParqueaderoDTO obtenerParqueaderoPorId(Long id) {
        Parqueadero parqueadero = parqueaderoRepository.findById(id).get();
               
        return modelMapper.map(parqueadero, ParqueaderoDTO.class);
    }
    
    public ParqueaderoDTO actualizarParqueadero(ParqueaderoDTO requestDTO) {
        Parqueadero parqueadero = parqueaderoRepository.findById(requestDTO.getId()).get();
                
        // Validar que el socio existe y tiene rol SOCIO
        
       
      
        Parqueadero newParqueadero = modelMapper.map(requestDTO, Parqueadero.class);
        parqueadero.setId(newParqueadero.getId());
        return convertToResponseDTO(updatedParqueadero);
    }
    
    public void eliminarParqueadero(Long id) {
        Parqueadero parqueadero = parqueaderoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parqueadero no encontrado con ID: " + id));
        
        // Verificar si hay vehículos actualmente parqueados
        Long vehiculosActuales = parqueaderoRepository.countVehiculosActuales(id);
        if (vehiculosActuales > 0) {
            throw new ParqueaderoException("No se puede eliminar el parqueadero porque tiene " + vehiculosActuales + " vehículos parqueados actualmente");
        }
        
        parqueaderoRepository.delete(parqueadero);
    }
    
    @Transactional(readOnly = true)
    public List<ParqueaderoResponseDTO> obtenerParqueaderosPorSocio(Long socioId) {
        Usuario socio = usuarioRepository.findById(socioId)
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con ID: " + socioId));
        
        return parqueaderoRepository.findBySocio(socio).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    private ParqueaderoResponseDTO convertToResponseDTO(Parqueadero parqueadero) {
        Long vehiculosActuales = parqueaderoRepository.countVehiculosActuales(parqueadero.getId());
        
        return ParqueaderoResponseDTO.builder()
                .id(parqueadero.getId())
                .nombre(parqueadero.getNombre())
                .capacidad(parqueadero.getCapacidad())
                .costoPorHora(parqueadero.getCostoPorHora())
                .fechaCreacion(parqueadero.getFechaCreacion())
                .vehiculosActuales(vehiculosActuales)
                .socio(ParqueaderoResponseDTO.SocioDTO.builder()
                        .id(parqueadero.getSocio().getId())
                        .nombre(parqueadero.getSocio().getNombre())
                        .email(parqueadero.getSocio().getEmail())
                        .build())
                .build();
    }
}
