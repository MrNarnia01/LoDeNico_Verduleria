package com.LoDeNico.Verduleria.Service.Empleado;

import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Repository.Empleado.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> getPersonaList(){
        return personaRepository.findAll();
    }

}
