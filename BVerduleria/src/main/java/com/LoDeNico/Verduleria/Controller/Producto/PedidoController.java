package com.LoDeNico.Verduleria.Controller.Producto;

import com.LoDeNico.Verduleria.Dto.Request.Producto.PedidoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.PedidoResponse;
import com.LoDeNico.Verduleria.Service.Producto.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {
    @Autowired
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPedido(@PathVariable Long id){
        PedidoResponse pedidoResponse = pedidoService.getPedido(id);
        if(pedidoResponse.getId()!=-1){
            return ResponseEntity.ok(pedidoResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pedidoResponse.getNB());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getPedidoList(){
        List<PedidoResponse> pedidoResponseList = pedidoService.getPedidoList();
        if(pedidoResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(pedidoResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pedidoResponseList.get(0).getNB());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id){
        int cod = pedidoService.deletePedido(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPedido(@RequestBody PedidoRequest pedidoRequest){
        PedidoResponse pedidoResponse = pedidoService.createPedido(pedidoRequest);
        if(pedidoResponse.getId()!=-1){
            return ResponseEntity.ok(pedidoResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pedidoResponse.getNB());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable Long id, @RequestBody PedidoRequest pedidoRequest){
        PedidoResponse pedidoResponse = pedidoService.updatePedido(pedidoRequest.getDetallePedidoRequestList(), id);
        if(pedidoResponse.getId()!=-1){
            return ResponseEntity.ok(pedidoResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pedidoResponse.getNB());
    }

}
