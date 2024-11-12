package com.LoDeNico.Verduleria.Controller.Empleado;

import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
import com.LoDeNico.Verduleria.Service.Empleado.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Long id){
        int del= personaService.deletePersona(id);
        if(del==0){
            return ResponseEntity.ok(del);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(del);
    }
}
