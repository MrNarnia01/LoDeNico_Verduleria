package com.LoDeNico.Verduleria.Controller.Proveedor;

import com.LoDeNico.Verduleria.Dto.Request.Proveedor.TrabajadorRequest;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;
import com.LoDeNico.Verduleria.Dto.Response.Proveedor.TrabajadorResponse;
import com.LoDeNico.Verduleria.Service.Proveedor.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trabajador")
public class TrabajadorController {
    @Autowired
    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTrabajador(@PathVariable Long id){
        TrabajadorResponse trabajadorResponse = trabajadorService.getTrabajador(id);
        if(trabajadorResponse.getId()!=-1){
            return ResponseEntity.ok(trabajadorResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(trabajadorResponse.getCodArea());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getTrabajadorList(){
        List<TrabajadorResponse> trabajadorResponseList = trabajadorService.getTrabajadorList();
        if(trabajadorResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(trabajadorResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(trabajadorResponseList.get(0).getCodArea());
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<?> getTrabajadorListByNombre(@PathVariable String nom){
        List<TrabajadorResponse> trabajadorResponseList = trabajadorService.getTrabajadorByNombre(nom);
        if(trabajadorResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(trabajadorResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(trabajadorResponseList.get(0).getCodArea());
    }

    @GetMapping("/ape/{ape}")
    public ResponseEntity<?> getTrabajadorListByApellido(@PathVariable String ape){
        List<TrabajadorResponse> trabajadorResponseList = trabajadorService.getTrabajadorByApellido(ape);
        if(trabajadorResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(trabajadorResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(trabajadorResponseList.get(0).getCodArea());
    }

    @GetMapping("/pue/{pue}")
    public ResponseEntity<?> getTrabajadorListByPuesto(@PathVariable String pue){
        List<TrabajadorResponse> trabajadorResponseList = trabajadorService.getTrabajadorByPuesto(pue);
        if(trabajadorResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(trabajadorResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(trabajadorResponseList.get(0).getCodArea());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrabajador(@PathVariable Long id){
        int cod = trabajadorService.deleteTrabajador(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTrabajador(@RequestBody TrabajadorRequest trabajadorRequest){
        TrabajadorResponse trabajadorResponse = trabajadorService.createTrabajador(trabajadorRequest);
        if(trabajadorResponse.getId()!=-1){
            return ResponseEntity.ok(trabajadorResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(trabajadorResponse.getCodArea());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateTrabajador(@RequestBody TrabajadorRequest trabajadorRequest, @PathVariable Long id){
        TrabajadorResponse trabajadorResponse = trabajadorService.updateTrabajador(trabajadorRequest, id);
        if(trabajadorResponse.getId()!=-1){
            return ResponseEntity.ok(trabajadorResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(trabajadorResponse.getCodArea());
    }

}
