package com.LoDeNico.Verduleria.Service.Empleado;

import com.LoDeNico.Verduleria.Entity.Cuenta.Cliente;
import com.LoDeNico.Verduleria.Entity.Empleado.Empleado;
import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Entity.Proveedor.Trabajador;
import com.LoDeNico.Verduleria.Repository.Cuenta.ClienteRepository;
import com.LoDeNico.Verduleria.Repository.Empleado.EmpleadoRepository;
import com.LoDeNico.Verduleria.Repository.Empleado.PersonaRepository;
import com.LoDeNico.Verduleria.Repository.Proveedor.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private final PersonaRepository personaRepository;
    private final ClienteRepository clienteRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final EmpleadoRepository empleadoRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository, ClienteRepository clienteRepository, TrabajadorRepository trabajadorRepository, EmpleadoRepository empleadoRepository) {
        this.personaRepository = personaRepository;
        this.clienteRepository = clienteRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public List<Persona> getPersonaList(){
        return personaRepository.findAll();
    }

    public int deletePersona(Long id){
        Optional<Persona> personaOptional=personaRepository.findById(id);
        if(personaOptional.isEmpty()) return 1001;
        Persona persona = personaOptional.get();
        List<Trabajador> trabajadorList = trabajadorRepository.findByPersona(persona);
        List<Cliente> clienteList = clienteRepository.findByPersona(persona);
        List<Empleado> empleadoList = empleadoRepository.findByPersona(persona);
        if(!empleadoList.isEmpty() ||
                !clienteList.isEmpty() ||
                !trabajadorList.isEmpty()) personaRepository.deleteById(id);
        else    return 1009;
        return 0;
    }



}
