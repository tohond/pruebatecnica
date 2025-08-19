package omarp.pruebatecnica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import omarp.pruebatecnica.entity.Parqueadero;
import omarp.pruebatecnica.entity.VehiculoParqueado;

@Repository
public interface VehiculoParqueadoRepository extends JpaRepository<VehiculoParqueado, Long> {
    boolean existsByVehiculo_Placa(String placa);
    long countByParqueadero(Parqueadero parqueadero);
    List<VehiculoParqueado> findByParqueadero(Parqueadero parqueadero);
    Optional<VehiculoParqueado> findByVehiculo_PlacaAndParqueadero(String placa, Parqueadero parqueadero);
}
