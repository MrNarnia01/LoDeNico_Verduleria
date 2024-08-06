package com.LoDeNico.Verduleria.Controller;

import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Request.PagoResquest;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;
import com.LoDeNico.Verduleria.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pago")
public class PagoController {
    @Autowired
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPago(@PathVariable Long id){
        PagoResponse pagoResponse = pagoService.getPago(id);
        if(pagoResponse.getId()!=-1){
            return ResponseEntity.ok(pagoResponse);
            }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pagoResponse.getMonto());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPago(@RequestBody PagoResquest pagoResquest){
        PagoResponse pagoResponse = pagoService.createPago(pagoResquest);
        if(pagoResponse.getId()!=-1){
            return ResponseEntity.ok(pagoResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pagoResponse.getMonto());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePago(@RequestBody PagoResquest pagoResquest, @PathVariable Long id){
        PagoResponse pagoResponse = pagoService.updatePago(pagoResquest, id);
        if(pagoResponse.getId()!=-1){
            return ResponseEntity.ok(pagoResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pagoResponse.getMonto());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePago(@PathVariable Long id){
        int pago = pagoService.deletePago(id);
        if(pago == 0){
            return ResponseEntity.ok(pago);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pago);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getPagoList(){
        List<PagoResponse> pagoResponseList = pagoService.getPagoList();
        if(pagoResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(pagoResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pagoResponseList.get(0).getMonto());
    }

    @GetMapping("/bus/{tipo}")
    public ResponseEntity<?> getPagoListBus(@PathVariable String tipo, @RequestBody MontoRequest montoRequest){
        List<PagoResponse> pagoResponseList = pagoService.getPagoListBus(tipo, montoRequest);
        if(pagoResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(pagoResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pagoResponseList.get(0).getMonto());
    }




}
