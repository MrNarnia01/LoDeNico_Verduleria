package com.LoDeNico.Verduleria.Dto.Request.Producto;

import com.LoDeNico.Verduleria.Dto.Request.Detalle.DetalleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    private Long idP;
    private List<DetalleRequest> detallePedidoRequestList;
}
