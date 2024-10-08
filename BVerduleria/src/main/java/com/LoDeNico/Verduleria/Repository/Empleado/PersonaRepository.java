package com.LoDeNico.Verduleria.Repository.Empleado;

import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByNombre(String nombre);
    List<Persona> findByApellido(String apellido);
    Optional<Persona> findByNombreAndApellidoAndCodAreaAndTel(String nombre, String apellido, int codArea, int tel);
}
