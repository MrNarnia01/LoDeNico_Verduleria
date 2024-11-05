package com.LoDeNico.Verduleria.Repository.Producto;

import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    Optional<Producto> findByNombre(String nombre);
    List<Producto> findByUnit(boolean unit);
    @Query(value="SELECT * FROM Producto WHERE precio BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Producto> serchByPrecio(double p1, double p2);

    List<Producto> findByPrecioBetween(double p1, double p2);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    @Query(value="SELECT * FROM Producto p WHERE ?1", nativeQuery = true)
    List<Producto> serchByVs(String bus);

    List<Producto> findBySoftDelete(boolean softDelete);
}
