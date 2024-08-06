package com.LoDeNico.Verduleria.Controller.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.Cuenta.CuentaCorrienteRequest;
import com.LoDeNico.Verduleria.Dto.Request.FechaRequest;
import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.CuentaCorrienteResponse;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;
import com.LoDeNico.Verduleria.Service.Cuenta.CuentaCorrienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cuenta")
public class CuentaCorrienteController {
    private final CuentaCorrienteService cuentaCorrienteService;

    public CuentaCorrienteController(CuentaCorrienteService cuentaCorrienteService) {
        this.cuentaCorrienteService = cuentaCorrienteService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCuenta(@PathVariable Long id){
        CuentaCorrienteResponse cuentaCorrienteResponse = cuentaCorrienteService.getCuentaCorriente(id);
        if(cuentaCorrienteResponse.getId()!=-1){
            return ResponseEntity.ok(cuentaCorrienteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuentaCorrienteResponse.getMonto());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCuentaList(){
        List<CuentaCorrienteResponse> cuentaCorrienteResponseList = cuentaCorrienteService.getCuentaCorrienteList();
        if(cuentaCorrienteResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(cuentaCorrienteResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuentaCorrienteResponseList.get(0).getMonto());
    }

    @GetMapping("/list/monto")
    public ResponseEntity<?> getCuentaListByMonto(@RequestBody MontoRequest montoRequest){
        List<CuentaCorrienteResponse> cuentaCorrienteResponseList = cuentaCorrienteService.getCuentaCorrienteListByMonto(montoRequest);
        if(cuentaCorrienteResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(cuentaCorrienteResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuentaCorrienteResponseList.get(0).getMonto());
    }

    @GetMapping("/list/fecha/{b}")
    public ResponseEntity<?> getCuentaListByFechas(@RequestBody FechaRequest fechaRequest, @PathVariable boolean b){
        List<CuentaCorrienteResponse> cuentaCorrienteResponseList = cuentaCorrienteService.getCuentaCorrienteListByFechas(fechaRequest, b);
        if(cuentaCorrienteResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(cuentaCorrienteResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuentaCorrienteResponseList.get(0).getMonto());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id){
        int cod = cuentaCorrienteService.deleteCuentaCorriente(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCuenta(@RequestBody CuentaCorrienteRequest cuentaCorrienteRequest){
        CuentaCorrienteResponse cuentaCorrienteResponse = cuentaCorrienteService.createCuentaCorriente(cuentaCorrienteRequest);
        if(cuentaCorrienteResponse.getId()!=-1){
            return ResponseEntity.ok(cuentaCorrienteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuentaCorrienteResponse.getMonto());
    }

    @PostMapping("/update/c/{id}")
    public ResponseEntity<?> updateCuenta(@RequestBody CuentaCorrienteRequest cuentaCorrienteRequest, @PathVariable Long id){
        CuentaCorrienteResponse cuentaCorrienteResponse = cuentaCorrienteService.updateCuentaCorriente(cuentaCorrienteRequest, id);
        if(cuentaCorrienteResponse.getId()!=-1){
            return ResponseEntity.ok(cuentaCorrienteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuentaCorrienteResponse.getMonto());
    }

    @PostMapping("/update/f/{id}")
    public ResponseEntity<?> updateCuentaFPago(@PathVariable Long id){
        CuentaCorrienteResponse cuentaCorrienteResponse = cuentaCorrienteService.updateFPago(id);
        if(cuentaCorrienteResponse.getId()!=-1){
            return ResponseEntity.ok(cuentaCorrienteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuentaCorrienteResponse.getMonto());
    }
}
