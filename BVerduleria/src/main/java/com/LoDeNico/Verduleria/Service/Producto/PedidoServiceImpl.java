package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.Detalle.DetalleRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.PedidoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Detalle.DetallePedidoResponse;
import com.LoDeNico.Verduleria.Dto.Response.Producto.PedidoResponse;
import com.LoDeNico.Verduleria.Entity.Detalle.detallePedido;
import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import com.LoDeNico.Verduleria.Entity.Proveedor.Proveedor;
import com.LoDeNico.Verduleria.Repository.Producto.PedidoRepository;
import com.LoDeNico.Verduleria.Repository.Producto.ProductoRepository;
import com.LoDeNico.Verduleria.Repository.Proveedor.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService{
    @Autowired
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProductoRepository productoRepository, ProveedorRepository proveedorRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    private PedidoResponse createPedidoResponse(Pedido pedido){
        List<DetallePedidoResponse> detallePedidoResponseList = new ArrayList<>();
        Long nB=-1L;
        Optional<Boleta> boletaOptional = pedidoRepository.serchByBoleta(pedido.getId());
        if(boletaOptional.isPresent()) nB = boletaOptional.get().getId();

        for (detallePedido dp: pedido.getDetallesPedido()){
            DetallePedidoResponse detallePedidoResponse = new DetallePedidoResponse(
                    dp.getId(),
                    pedido.getId(),
                    dp.getProducto().getNombre(),
                    dp.getProducto().isUnit(),
                    dp.getCaja(),
                    dp.getCantidad()
            );
            detallePedidoResponseList.add(detallePedidoResponse);
        }
        return new PedidoResponse(
                pedido.getId(),
                pedido.getProveedor().getNegocio(),
                pedido.getFPedido(),
                detallePedidoResponseList,
                nB);
    }

    public PedidoResponse getPedido(Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()){
            return createPedidoResponse(pedidoOptional.get());
        }else return new PedidoResponse(-1L,"",null,null,1002L);
    }

    public List<PedidoResponse> getPedidoList(){
        List<Pedido> pedidoList = pedidoRepository.findAll();
        List<PedidoResponse> pedidoResponseList = new ArrayList<>();
        if(!pedidoList.isEmpty()){
            for (Pedido p: pedidoList){
                pedidoResponseList.add(createPedidoResponse(p));
            }
        }else{
            pedidoResponseList.add(new PedidoResponse(-1L,"",null,null,1001L));
        }

        return pedidoResponseList;
    }

    public int deletePedido(Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()){
            pedidoRepository.deleteById(id);
            return 0;
        }

        return 1002;
    }

    public PedidoResponse createPedido(PedidoRequest pedidoRequest){
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(pedidoRequest.getIdP());
        boolean b = true;

        if (proveedorOptional.isEmpty()) b = false;

        for (DetalleRequest dr: pedidoRequest.getDetallePedidoRequestList()){
            Optional<Producto> productoOptional = productoRepository.findById(dr.getIdP());
            if(productoOptional.isEmpty()) b = false;
            if(dr.getCaja()<0) b = false;
            if(dr.getCantidad()<=0) b = false;
        }

        if(b){
            Pedido pedido = new Pedido();
            List<detallePedido> detallePedidoList = new ArrayList<>();
            pedido.setProveedor(proveedorOptional.get());

            for (DetalleRequest dr: pedidoRequest.getDetallePedidoRequestList()){
                detallePedido detallePedido = new detallePedido();
                Optional<Producto> productoOptional = productoRepository.findById(dr.getIdP());
                detallePedido.setCaja(dr.getCaja());
                detallePedido.setCantidad(dr.getCantidad());
                detallePedido.setProducto(productoOptional.get());
                detallePedidoList.add(detallePedido);
            }

            pedido.setDetallesPedido(detallePedidoList);

            pedido = pedidoRepository.save(pedido);
            return createPedidoResponse(pedido);

        }else return new PedidoResponse(-1L,"",null,null,1003L);
    }

    public PedidoResponse updatePedido(List<DetalleRequest> detalleRequestList, Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        boolean b = true;

        if (pedidoOptional.isEmpty()) return new PedidoResponse(-1L,"",null,null,1003L);

        for (DetalleRequest dr: detalleRequestList){
            Optional<Producto> productoOptional = productoRepository.findById(dr.getIdP());
            if(productoOptional.isEmpty()) b = false;
            if(dr.getCaja()<0) b = false;
            if(dr.getCantidad()<=0) b = false;
        }

        if(b){
            Pedido pedido = pedidoOptional.get();
            List<detallePedido> detallePedidoList = new ArrayList<>();

            for (DetalleRequest dr: detalleRequestList){
                detallePedido detallePedido = new detallePedido();
                Optional<Producto> productoOptional = productoRepository.findById(dr.getIdP());
                detallePedido.setCaja(dr.getCaja());
                detallePedido.setCantidad(dr.getCantidad());
                detallePedido.setProducto(productoOptional.get());
                detallePedidoList.add(detallePedido);
            }

            pedido.setDetallesPedido(detallePedidoList);

            pedido = pedidoRepository.save(pedido);
            return createPedidoResponse(pedido);

        }else return new PedidoResponse(-1L,"",null,null,1003L);
    }






}
