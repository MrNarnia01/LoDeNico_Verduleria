package com.LoDeNico.Verduleria.Repository.Cuenta;

import com.LoDeNico.Verduleria.Entity.Cuenta.Cliente;
import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByCalle(String calle);

    List<Cliente> findByPersona(Persona persona);

    @Query(value="SELECT c.* FROM persona p INNER JOIN cliente c ON p.id = c.id_persona WHERE UPPER(p.nombre) LIKE UPPER(CONCAT('%', :nom ,'%')) ", nativeQuery = true)
    List<Cliente> serchByBus(@Param("nom") String nom);


}
