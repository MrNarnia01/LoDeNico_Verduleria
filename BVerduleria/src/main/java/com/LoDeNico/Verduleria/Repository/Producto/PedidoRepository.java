package com.LoDeNico.Verduleria.Repository.Producto;

import com.LoDeNico.Verduleria.Entity.Cierre.CierreLote;
import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    @Query(value="SELECT * FROM boleta WHERE id_pedido = ?1", nativeQuery = true)
    Optional<Boleta> serchByBoleta(Long id);

    @Query(value="SELECT p.* FROM pedido p INNER JOIN boleta b ON p.id = b.id_pedido", nativeQuery = true)
    List<Pedido> serchByBoletaJoin();

    @Query(value="SELECT p.* FROM pedido p LEFT JOIN boleta b ON p.id = b.id_pedido", nativeQuery = true)
    List<Pedido> serchByBoletaLeft();

    List<Pedido> findByfPedidoBetween(Timestamp inicio, Timestamp cierre);

    List<Pedido> findByProveedorId(Long proveedorId);
}
