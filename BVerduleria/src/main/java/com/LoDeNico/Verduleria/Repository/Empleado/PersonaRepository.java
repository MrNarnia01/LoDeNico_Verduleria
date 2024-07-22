package com.LoDeNico.Verduleria.Repository.Empleado;

import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByNombre(String nombre);
    List<Persona> findByApellido(String apellido);
}
