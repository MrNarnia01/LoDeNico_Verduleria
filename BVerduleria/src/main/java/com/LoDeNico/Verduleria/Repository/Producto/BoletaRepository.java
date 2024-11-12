package com.LoDeNico.Verduleria.Repository.Producto;

import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface BoletaRepository extends JpaRepository<Boleta,Long> {
    List<Boleta> findByPaga(boolean paga);
    @Query(value="SELECT * FROM Boleta WHERE monto BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Boleta> serchByMonto(double m1, double m2);
    Optional<Boleta> findByPedido(Pedido pedido);

    @Query(value="SELECT b.* FROM boleta b WHERE :bus", nativeQuery = true)
    List<Boleta> serchByBus(@Param("bus") String bus);

    List<Boleta> findByfReciboBetween(Timestamp inicio, Timestamp cierre);

}
