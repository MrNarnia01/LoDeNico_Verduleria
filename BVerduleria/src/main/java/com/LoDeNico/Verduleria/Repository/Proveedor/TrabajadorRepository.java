package com.LoDeNico.Verduleria.Repository.Proveedor;

import com.LoDeNico.Verduleria.Entity.Proveedor.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador,Long> {
    List<Trabajador> findByNombre(String nombre);
    List<Trabajador> findByApellido(String apellido);
    List<Trabajador> findByPuesto(String puesto);
}
