package com.LoDeNico.Verduleria.Controller.Producto;

import com.LoDeNico.Verduleria.Dto.Request.BusRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.LoteRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.LoteResponse;
import com.LoDeNico.Verduleria.Service.Producto.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lote")
public class LoteController {
    @Autowired
    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getLote(@PathVariable Long id){
        LoteResponse loteResponse = loteService.getLote(id);
        if(loteResponse.getId()!=-1){
            return ResponseEntity.ok(loteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loteResponse.getNLote());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getLoteList(){
        List<LoteResponse> loteResponseList = loteService.getLoteList();
        if(loteResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(loteResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loteResponseList.get(0).getNLote());
    }

    @PostMapping("/dias")
    public ResponseEntity<?> getLoteListByDias(@RequestBody BusRequest busRequest){
        List<LoteResponse> loteResponseList = loteService.getLoteListBus(busRequest);
        return ResponseEntity.ok(loteResponseList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLote(@RequestBody LoteRequest loteRequest){
        LoteResponse loteResponse = loteService.createLote(loteRequest);
        if(loteResponse.getId()!=-1){
            return ResponseEntity.ok(loteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loteResponse.getNLote());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateLote(@RequestBody LoteRequest loteRequest, @PathVariable Long id){
        LoteResponse loteResponse = loteService.updateLote(loteRequest, id);
        if(loteResponse.getId()!=-1){
            return ResponseEntity.ok(loteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loteResponse.getNLote());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLote(@PathVariable Long id){
        int cod = loteService.deleteLote(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }
}
