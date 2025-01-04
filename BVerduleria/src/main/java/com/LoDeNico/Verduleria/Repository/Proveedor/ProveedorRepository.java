package com.LoDeNico.Verduleria.Repository.Proveedor;

import com.LoDeNico.Verduleria.Entity.Proveedor.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
    Optional<Proveedor> findByNegocio(String negocio);

}
