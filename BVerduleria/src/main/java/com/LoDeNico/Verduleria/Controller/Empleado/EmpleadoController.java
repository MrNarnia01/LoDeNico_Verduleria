package com.LoDeNico.Verduleria.Controller.Empleado;

import com.LoDeNico.Verduleria.Dto.Request.Empleado.EmpleadoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Empleado.EmpleadoResponse;
import com.LoDeNico.Verduleria.Service.Empleado.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/empleado")
public class EmpleadoController {
    @Autowired
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmpleado(@PathVariable Long id){
        EmpleadoResponse empleadoResponse = empleadoService.getEmpleado(id);
        if(!empleadoResponse.getNombre().equals("Error")){
            return ResponseEntity.ok(empleadoResponse);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empleadoResponse.getId());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getEmpleadoList(){
        List<EmpleadoResponse> empleadoResponseList = empleadoService.getEmpleadoList();
        if(!empleadoResponseList.get(0).getNombre().equals("Error")){
            return ResponseEntity.ok(empleadoResponseList);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empleadoResponseList.get(0).getId());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmpleado(@RequestBody EmpleadoRequest empleadoRequest){
        EmpleadoResponse empleadoResponse = empleadoService.createEmpleado(empleadoRequest);
        if(!empleadoResponse.getNombre().equals("Error")){
            return ResponseEntity.ok(empleadoResponse);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empleadoResponse.getId());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateEmpleado(@RequestBody EmpleadoRequest empleadoRequest, @PathVariable Long id){
        EmpleadoResponse empleadoResponse = empleadoService.updateEmpleado(empleadoRequest, id);
        if(!empleadoResponse.getNombre().equals("Error")){
            return ResponseEntity.ok(empleadoResponse);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empleadoResponse.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Long id){
        int cod = empleadoService.deleteEmpleado(id);
        if(cod == 0 ){
            return ResponseEntity.ok(cod);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

}
