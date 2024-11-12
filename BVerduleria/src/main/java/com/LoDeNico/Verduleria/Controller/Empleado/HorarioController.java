package com.LoDeNico.Verduleria.Controller.Empleado;

import com.LoDeNico.Verduleria.Dto.Request.Empleado.HorarioRequest;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.HorarioResponse;
import com.LoDeNico.Verduleria.Service.Empleado.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/horario")
public class HorarioController {
    @Autowired
    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHorario(@PathVariable Long id){
        HorarioResponse horarioResponse = horarioService.getHorario(id);
        if(horarioResponse.getId()!=-1){
            return ResponseEntity.ok(horarioResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(horarioResponse.getNombre());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getHorarioList(){
        List<HorarioResponse> horarioResponseList = horarioService.getHorarioList();
        if(horarioResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(horarioResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(horarioResponseList.get(0).getNombre());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHorario(@PathVariable Long id){
        int cod = horarioService.deleteHorario(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createHorario(@RequestBody HorarioRequest horarioRequest){
        HorarioResponse horarioResponse = horarioService.createHorario(horarioRequest);
        if(horarioResponse.getId()!=-1){
            return ResponseEntity.ok(horarioResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(horarioResponse.getNombre());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateHorario(@RequestBody HorarioRequest horarioRequest, @PathVariable Long id){
        HorarioResponse horarioResponse = horarioService.updateHorario(horarioRequest, id);
        if(horarioResponse.getId()!=-1){
            return ResponseEntity.ok(horarioResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(horarioResponse.getNombre());
    }

}
