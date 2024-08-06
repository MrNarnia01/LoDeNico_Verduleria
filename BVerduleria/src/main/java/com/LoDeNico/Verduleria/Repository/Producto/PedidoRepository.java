package com.LoDeNico.Verduleria.Repository.Producto;

import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    @Query(value="SELECT * FROM Boleta WHERE idPedido = ?1", nativeQuery = true)
    Optional<Boleta> serchByBoleta(Long id);
}
