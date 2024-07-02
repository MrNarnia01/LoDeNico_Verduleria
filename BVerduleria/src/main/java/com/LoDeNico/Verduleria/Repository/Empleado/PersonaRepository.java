package com.LoDeNico.Verduleria.Repository.Empleado;

import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
