package com.LoDeNico.Verduleria.Controller.Cuenta;

import com.LoDeNico.Verduleria.Dto.Request.Cuenta.ClienteRequest;
import com.LoDeNico.Verduleria.Dto.Response.Cuenta.ClienteResponse;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;
import com.LoDeNico.Verduleria.Service.Cuenta.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCliente(@PathVariable Long id){
        ClienteResponse clienteResponse = clienteService.getCliente(id);
        if(clienteResponse.getId()!=-1){
            return ResponseEntity.ok(clienteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponse.getCodArea());
    }

    @GetMapping("/list/all")
    public ResponseEntity<?> getClienteList(){
        List<ClienteResponse> clienteResponseList = clienteService.getClienteList();
        if(clienteResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(clienteResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponseList.get(0).getCodArea());
    }

    @GetMapping("/list/nom/{nom}")
    public ResponseEntity<?> getClienteListByNombre(@PathVariable String nom){
        List<ClienteResponse> clienteResponseList = clienteService.getClienteListByNombre(nom);
        if(clienteResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(clienteResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponseList.get(0).getCodArea());
    }

    @GetMapping("/list/ape/{ape}")
    public ResponseEntity<?> getClienteListByApellido(@PathVariable String ape){
        List<ClienteResponse> clienteResponseList = clienteService.getClienteListByApellido(ape);
        if(clienteResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(clienteResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponseList.get(0).getCodArea());
    }

    @GetMapping("/list/calle/{cal}")
    public ResponseEntity<?> getClienteListByCalle(@PathVariable String cal){
        List<ClienteResponse> clienteResponseList = clienteService.getClienteListByCalle(cal);
        if(clienteResponseList.get(0).getId()!=-1){
            return ResponseEntity.ok(clienteResponseList);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponseList.get(0).getCodArea());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id){
        int cod = clienteService.deleteCliente(id);
        if(cod == 0){
            return ResponseEntity.ok(cod);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cod);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCliente(@RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.createCliente(clienteRequest);
        if(clienteResponse.getId()!=-1){
            return ResponseEntity.ok(clienteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponse.getCodArea());
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> getCliente(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest){
        ClienteResponse clienteResponse = clienteService.updateCliente(clienteRequest, id);
        if(clienteResponse.getId()!=-1){
            return ResponseEntity.ok(clienteResponse);
        }else   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponse.getCodArea());
    }

}
