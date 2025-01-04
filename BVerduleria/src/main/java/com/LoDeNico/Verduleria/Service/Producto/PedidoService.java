package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.BusRequest;
import com.LoDeNico.Verduleria.Dto.Request.Detalle.DetalleRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.PedidoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.PedidoResponse;

import java.util.List;

public interface PedidoService {
    public PedidoResponse getPedido(Long id);
    public List<PedidoResponse> getPedidoList();
    public int deletePedido(Long id);
    public PedidoResponse createPedido(PedidoRequest pedidoRequest);
    public PedidoResponse updatePedido(List<DetalleRequest> detalleRequestList, Long id);
    public List<PedidoResponse> busPedido(BusRequest busRequest);
}
