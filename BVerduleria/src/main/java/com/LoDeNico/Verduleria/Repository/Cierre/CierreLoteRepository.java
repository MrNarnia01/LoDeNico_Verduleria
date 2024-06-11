package com.LoDeNico.Verduleria.Repository.Cierre;

import com.LoDeNico.Verduleria.Entity.Cierre.CierreLote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CierreLoteRepository extends JpaRepository<CierreLote,Long> {
    List<CierreLote> findByTipo(boolean tipo);
    @Query(value="SELECT * FROM Cierre WHERE monto BETWEEN ?1 AND ?2", nativeQuery = true)
    List<CierreLote> serchByMonto(double m1, double m2);
}
