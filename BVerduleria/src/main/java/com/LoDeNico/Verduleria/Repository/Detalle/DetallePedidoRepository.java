package com.LoDeNico.Verduleria.Repository.Detalle;

import com.LoDeNico.Verduleria.Entity.Detalle.detallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<detallePedido,Long> {
    @Query(value="SELECT id FROM detalle_pedido WHERE id_pedido=?1", nativeQuery = true)
    List<Long> findByPedido(Long idP);
}
