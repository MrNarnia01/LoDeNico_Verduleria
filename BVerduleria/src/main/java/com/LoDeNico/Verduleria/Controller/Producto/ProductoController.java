package com.LoDeNico.Verduleria.Controller.Producto;
import com.LoDeNico.Verduleria.Dto.Request.Producto.BusPRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.ProductoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.ProductoResponse;
import com.LoDeNico.Verduleria.Service.Producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto")
public class ProductoController {
    @Autowired
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProducto(@PathVariable Long id){
        ProductoResponse productoResponse = productoService.getProducto(id);
        if(productoResponse.getId()!=-1){
            return ResponseEntity.ok(productoResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productoResponse.getStock());
    }

    @GetMapping("/list/{b}")
    public ResponseEntity<?> getProductoList(@PathVariable boolean b){
        List<ProductoResponse> productoResponseList = productoService.getProductoList(b);
        if(productoResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(productoResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productoResponseList.get(0).getStock());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        int cod = productoService.deleteProducto(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @PostMapping("/updateStock/{id}")
    public ResponseEntity<?> updateProductoStock(@PathVariable Long id, @RequestBody int n){
        int cod = productoService.updateStock(id, n);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProducto(@RequestBody ProductoRequest productoRequest){
        ProductoResponse productoResponse = productoService.createProducto(productoRequest);
        if(productoResponse.getId()!=-1){
            return ResponseEntity.ok(productoResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productoResponse.getStock());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProducto(@RequestBody ProductoRequest productoRequest, @PathVariable Long id){
        ProductoResponse productoResponse = productoService.updateProducto(productoRequest, id);
        if(productoResponse.getId()!=-1){
            return ResponseEntity.ok(productoResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productoResponse.getStock());
    }

    @GetMapping("/bus")
    public ResponseEntity<?> getProductoList(@RequestBody BusPRequest busPRequest){
        List<ProductoResponse> productoResponseList = productoService.busProducto(busPRequest);
        if(productoResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(productoResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productoResponseList.get(0).getStock());
    }



}
