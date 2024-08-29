package com.LoDeNico.Verduleria.Dto.Response.Producto;

import com.LoDeNico.Verduleria.Dto.Response.Detalle.DetallePedidoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private Long id;
    private Long idP;
    private String negocio;
    private Timestamp fPedido;
    private List<DetallePedidoResponse> detallePedidoResponseList;
    private Long nB;
}
