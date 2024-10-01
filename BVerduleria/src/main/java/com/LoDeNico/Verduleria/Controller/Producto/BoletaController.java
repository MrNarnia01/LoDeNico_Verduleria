package com.LoDeNico.Verduleria.Controller.Producto;

import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.BoletaRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.BoletaResponse;
import com.LoDeNico.Verduleria.Service.Producto.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/boleta")
public class BoletaController {
    @Autowired
    private final BoletaService boletaService;

    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBoleta(@PathVariable Long id){
        BoletaResponse boletaResponse = boletaService.getBoleta(id);
        if(boletaResponse.getNB()!=-1){
            return ResponseEntity.ok(boletaResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(boletaResponse.getIdP());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getBoletaList(){
        List<BoletaResponse> boletaResponseList = boletaService.getBoletaList();
        if(boletaResponseList.get(0).getNB()!=-1){
            return ResponseEntity.ok(boletaResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(boletaResponseList.get(0).getIdP());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBoleta(@PathVariable Long id){
        int cod = boletaService.deleteBoleta(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @GetMapping("/list/mon")
    public ResponseEntity<?> getBoletaListByMonto(@RequestBody MontoRequest montoRequest){
        List<BoletaResponse> boletaResponseList = boletaService.getBoletaListByMonto(montoRequest);
        if(boletaResponseList.get(0).getNB()!=-1){
            return ResponseEntity.ok(boletaResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(boletaResponseList.get(0).getIdP());
    }

    @GetMapping("/list/pag/{p}")
    public ResponseEntity<?> getBoletaListByPago(@PathVariable boolean p){
        List<BoletaResponse> boletaResponseList = boletaService.getBoletaListByPago(p);
        if(boletaResponseList.get(0).getNB()!=-1){
            return ResponseEntity.ok(boletaResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(boletaResponseList.get(0).getIdP());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBoleta(@RequestBody BoletaRequest boletaRequest){
        BoletaResponse boletaResponse = boletaService.createBoleta(boletaRequest);
        if(boletaResponse.getNB()!=-1){
            return ResponseEntity.ok(boletaResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(boletaResponse.getIdP());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBoleta(@RequestBody BoletaRequest boletaRequest,@PathVariable Long id){
        BoletaResponse boletaResponse = boletaService.updateBoleta(boletaRequest, id);
        if(boletaResponse.getNB()!=-1){
            return ResponseEntity.ok(boletaResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(boletaResponse.getIdP());
    }

}
