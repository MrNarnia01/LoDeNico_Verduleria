package com.LoDeNico.Verduleria.Repository.Producto;

import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoletaRepository extends JpaRepository<Boleta,Long> {
    List<Boleta> findByPaga(boolean paga);
    @Query(value="SELECT * FROM Boleta WHERE monto BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Boleta> serchByMonto(double m1, double m2);
}
