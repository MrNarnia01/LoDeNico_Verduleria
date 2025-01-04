package com.LoDeNico.Verduleria.Repository.Proveedor;

import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Entity.Proveedor.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador,Long> {
    List<Trabajador> findByPuesto(String puesto);

    List<Trabajador> findByPersona(Persona persona);
}
