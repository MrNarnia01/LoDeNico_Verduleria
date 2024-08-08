package com.LoDeNico.Verduleria.Repository.Producto;

import com.LoDeNico.Verduleria.Entity.Producto.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface LoteRepository extends JpaRepository<Lote,Long> {
    @Query(value="SELECT * FROM Lote  WHERE fVenci BETWEEN :inicio AND :cierre", nativeQuery = true)
    List<Lote> findByDias(@Param("inicio") Timestamp inicio, @Param("cierre") Timestamp cierre);
}
