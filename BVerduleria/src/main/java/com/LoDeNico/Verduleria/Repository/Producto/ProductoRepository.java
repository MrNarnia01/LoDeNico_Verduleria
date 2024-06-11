package com.LoDeNico.Verduleria.Repository.Producto;

import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByNombre(String nombre);
    List<Producto> findByUnit(boolean unit);
    @Query(value="SELECT * FROM Producto WHERE precio BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Producto> serchByPrecio(double p1, double p2);
    @Query(value="SELECT * FROM Producto WHERE stock BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Producto> serchByStock(int s1, int s2);
}
