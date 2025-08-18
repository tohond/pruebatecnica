package omarp.pruebatecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import omarp.pruebatecnica.entity.Parqueadero;
import omarp.pruebatecnica.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Long> {
    
    List<Parqueadero> findBySocio(User socio);
    
    Optional<Parqueadero> findByNombre(String nombre);
    
    @Query("SELECT p FROM Parqueadero p WHERE p.socio.id = :socioId")
    List<Parqueadero> findParqueaderosBySocioId(@Param("socioId") Long socioId);
    
    @Query("SELECT COUNT(vp) FROM VehiculoParqueado vp WHERE vp.parqueadero.id = :parqueaderoId")
    Long countVehiculosActuales(@Param("parqueaderoId") Long parqueaderoId);
}
