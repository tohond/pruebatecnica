package omarp.pruebatecnica.service;


import lombok.RequiredArgsConstructor;
import omarp.pruebatecnica.dto.ParqueaderoDTO;
import omarp.pruebatecnica.dto.VehiculoDTO;
import omarp.pruebatecnica.entity.Parqueadero;
import omarp.pruebatecnica.entity.Vehiculo;
import omarp.pruebatecnica.entity.VehiculoParqueado;
import omarp.pruebatecnica.repository.ParqueaderoRepository;
import omarp.pruebatecnica.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ParqueaderoService  {
    
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
    
    public ParqueaderoDTO actualizarParqueadero (ParqueaderoDTO requestDTO) throws Exception {
        Parqueadero parqueadero = parqueaderoRepository.findById(requestDTO.getId())
				.orElseThrow(() -> new Exception("Parqueadero no encontrado con ID: " + requestDTO.getId()));
        
        // Validar que el socio existe y tiene rol SOCIO
        
       parqueadero = modelMapper.map(requestDTO, Parqueadero.class);
      
        
        return modelMapper.map(parqueaderoRepository.save(parqueadero), ParqueaderoDTO.class);
    }
    
    public void eliminarParqueadero(Long id) throws Exception {
        Parqueadero parqueadero = parqueaderoRepository.findById(id)
                .orElseThrow(() -> new Exception("Parqueadero no encontrado con ID: " + id));
        
        // Verificar si hay vehículos actualmente parqueados
        Long vehiculosActuales = parqueaderoRepository.countVehiculosActuales(id);
        if (vehiculosActuales > 0) {
            throw new Exception("No se puede eliminar el parqueadero porque tiene " + vehiculosActuales + " vehículos parqueados actualmente");
        }
        
        parqueaderoRepository.delete(parqueadero);
    }
    
    @Transactional(readOnly = true)
    public List<VehiculoDTO> obtenerVehiculosPorParqueadero(Long id)	 throws Exception {
        
    	Parqueadero parqueadero = parqueaderoRepository.findById(id)
                .orElseThrow(() -> new Exception("Parqueadero no encontrado con ID: " + id));
    	
    			Set<VehiculoParqueado> vehiculosp = parqueadero.getVehiculosParqueados();
    			List<VehiculoDTO> vehiculosDTO = vehiculosp.stream()
						.map(v -> modelMapper.map(v.getVehiculo(), VehiculoDTO.class))
						.collect(Collectors.toList());
    			return vehiculosDTO;
    			
    	
    	
    }
    
    public VehiculoParqueaderoDTO registrarentrada(Long parqueaderoId, VehiculoDTO vehiculoDTO, LocalDateTime time) throws Exception {
		Parqueadero parqueadero = parqueaderoRepository.findById(parqueaderoId).get();
		VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
		vehiculoParqueado.setVehiculo(modelMapper.map(vehiculoDTO, Vehiculo.class));
		vehiculoParqueado.setParqueadero(parqueadero);
		vehiculoParqueado.setFechaIngreso(time);
		parqueadero.getVehiculosParqueados().add(vehiculoParqueado);
		parqueaderoRepository.save(parqueadero);
		 
		return modelMapper.map(vehiculoParqueado, VehiculoParqueaderoDTO.class);
    }
    
    public VehiculoParqueaderoDTO registrarSalida(Long parqueaderoId, String placa, LocalDateTime time) throws Exception {
		Parqueadero parqueadero = parqueaderoRepository.findById(parqueaderoId).get();
		Vehiculo v = new Vehiculo(placa);
		if (!parqueadero.getVehiculosParqueados().contains(v)) {
			
			
			throw new Exception("El vehículo con placa " + placa + " no está parqueado en el parqueadero con ID: " + parqueaderoId);
		}
		VehiculoParqueado vehiculoParqueado = parqueadero.getVehiculosParqueados().
				
    }
		
    
    
}
