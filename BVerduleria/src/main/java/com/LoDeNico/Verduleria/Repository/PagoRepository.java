package com.LoDeNico.Verduleria.Repository;

import com.LoDeNico.Verduleria.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago,Long> {
    List<Pago> findByTipo(String tipo);
    @Query(value="SELECT * FROM Pago WHERE monto BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Pago> serchByMonto(double m1, double m2);

}
