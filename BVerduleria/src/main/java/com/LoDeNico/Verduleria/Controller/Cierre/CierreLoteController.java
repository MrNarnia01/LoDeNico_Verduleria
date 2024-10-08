package com.LoDeNico.Verduleria.Controller.Cierre;

import com.LoDeNico.Verduleria.Dto.Request.Cierre.BusRequest;
import com.LoDeNico.Verduleria.Dto.Request.Cierre.CierreRequest;
import com.LoDeNico.Verduleria.Entity.Cierre.CierreLote;
import com.LoDeNico.Verduleria.Service.Cierre.CierreLoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cierre")
public class CierreLoteController {
    @Autowired
    private final CierreLoteService cierreLoteService;

    public CierreLoteController(CierreLoteService cierreLoteService) {
        this.cierreLoteService = cierreLoteService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLote(@RequestBody CierreRequest cierreRequest){
        CierreLote cierreLote = cierreLoteService.createCierre(cierreRequest);
        if(cierreLote.getId()!=-1){
            return ResponseEntity.ok(cierreLote);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cierreLote.getMonto());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateLote(@RequestBody CierreRequest cierreRequest, @PathVariable Long id){
        CierreLote cierreLote = cierreLoteService.updateCierre(cierreRequest,id);
        if(cierreLote.getId()!=-1){
            return ResponseEntity.ok(cierreLote);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cierreLote.getMonto());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCierre(@PathVariable Long id){
        int cod = cierreLoteService.deleteCierre(id);
        if(cod==0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @GetMapping
    public ResponseEntity<?> getCierreBus(@RequestBody BusRequest busRequest){
        List<CierreLote> cierreLoteList = cierreLoteService.cierreLoteBus(busRequest);
        if(!cierreLoteList.isEmpty()){
            return ResponseEntity.ok(cierreLoteList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(1001);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCierreList(){
        List<CierreLote> cierreLoteList = cierreLoteService.cierreLoteList();
        if(!cierreLoteList.isEmpty()){
            return ResponseEntity.ok(cierreLoteList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(1001);
    }

}
