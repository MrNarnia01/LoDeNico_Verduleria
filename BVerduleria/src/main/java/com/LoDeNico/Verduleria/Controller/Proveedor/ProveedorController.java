package com.LoDeNico.Verduleria.Controller.Proveedor;

import com.LoDeNico.Verduleria.Dto.Request.Proveedor.ProveedorRequest;
import com.LoDeNico.Verduleria.Dto.Response.Proveedor.ProveedorResponse;
import com.LoDeNico.Verduleria.Service.Proveedor.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/proveedor")
public class ProveedorController {
    @Autowired
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProveedor(@PathVariable Long id){
        ProveedorResponse proveedorResponse = proveedorService.getProveedor(id);
        if(proveedorResponse.getId()!=-1){
            return ResponseEntity.ok(proveedorResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(proveedorResponse.getAltura());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getProveedorList(){
        List<ProveedorResponse> proveedorResponseList = proveedorService.getProveedorList();
        if(proveedorResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(proveedorResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(proveedorResponseList.get(0).getAltura());
    }

    @GetMapping("/get/{nec}")
    public ResponseEntity<?> getProveedor(@PathVariable String nec){
        ProveedorResponse proveedorResponse = proveedorService.getProveedorByNegocio(nec);
        if(proveedorResponse.getId()!=-1){
            return ResponseEntity.ok(proveedorResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(proveedorResponse.getAltura());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable Long id){
        int cod = proveedorService.deleteProveedor(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProveedor(@RequestBody ProveedorRequest proveedorRequest){
        ProveedorResponse proveedorResponse = proveedorService.createProveedor(proveedorRequest);
        if(proveedorResponse.getId()!=-1){
            return ResponseEntity.ok(proveedorResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(proveedorResponse.getAltura());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProveedor(@RequestBody ProveedorRequest proveedorRequest, @PathVariable Long id){
        ProveedorResponse proveedorResponse = proveedorService.updateProveedor(proveedorRequest, id);
        if(proveedorResponse.getId()!=-1){
            return ResponseEntity.ok(proveedorResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(proveedorResponse.getAltura());
    }

}
