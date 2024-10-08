package com.LoDeNico.Verduleria.Controller.Empleado;

import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Service.Empleado.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/persona")
public class PersonaController {
    @Autowired
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getPersonaList(){
        List<Persona> personaList = personaService.getPersonaList();
        return ResponseEntity.ok(personaList);
    }
}
