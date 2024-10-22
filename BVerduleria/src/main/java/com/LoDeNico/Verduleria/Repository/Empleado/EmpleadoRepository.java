package com.LoDeNico.Verduleria.Repository.Empleado;

import com.LoDeNico.Verduleria.Entity.Empleado.Empleado;
import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByPersona(Persona persona);
}
