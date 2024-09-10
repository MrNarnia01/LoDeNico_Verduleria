package com.LoDeNico.Verduleria.Repository.Detalle;

import com.LoDeNico.Verduleria.Entity.Detalle.detalleBoleta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleBoletaRepository extends JpaRepository<detalleBoleta,Long> {
    @Transactional
    @Modifying
    @Query(value="DELETE FROM detalle_boleta WHERE id_boleta=?1", nativeQuery = true)
    void deleteByBoleta(Long id);
}
