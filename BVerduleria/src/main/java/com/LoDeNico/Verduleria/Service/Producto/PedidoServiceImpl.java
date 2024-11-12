package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.BusRequest;
import com.LoDeNico.Verduleria.Dto.Request.Detalle.DetalleRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.PedidoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Detalle.DetallePedidoResponse;
import com.LoDeNico.Verduleria.Dto.Response.Producto.PedidoResponse;
import com.LoDeNico.Verduleria.Entity.Detalle.detallePedido;
import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import com.LoDeNico.Verduleria.Entity.Proveedor.Proveedor;
import com.LoDeNico.Verduleria.Repository.Detalle.DetallePedidoRepository;
import com.LoDeNico.Verduleria.Repository.Producto.BoletaRepository;
import com.LoDeNico.Verduleria.Repository.Producto.PedidoRepository;
import com.LoDeNico.Verduleria.Repository.Producto.ProductoRepository;
import com.LoDeNico.Verduleria.Repository.Proveedor.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PedidoServiceImpl implements PedidoService{
    @Autowired
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final BoletaRepository boletaRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProductoRepository productoRepository, ProveedorRepository proveedorRepository, DetallePedidoRepository detallePedidoRepository, BoletaRepository boletaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.boletaRepository = boletaRepository;
    }

    private PedidoResponse createPedidoResponse(Pedido pedido){
        List<DetallePedidoResponse> detallePedidoResponseList = new ArrayList<>();
        Long nB=-1L;
        Optional<Boleta> boletaOptional = boletaRepository.findByPedido(pedido);
        if(boletaOptional.isPresent()) nB = boletaOptional.get().getNB();

        for (detallePedido dp: pedido.getDetallesPedido()){
            DetallePedidoResponse detallePedidoResponse = new DetallePedidoResponse(
                    dp.getId(),
                    pedido.getId(),
                    dp.getProducto().getNombre(),
                    dp.getProducto().getId(),
                    dp.getCaja(),
                    dp.getCantidad()
            );
            detallePedidoResponseList.add(detallePedidoResponse);
        }
        return new PedidoResponse(
                pedido.getId(),
                pedido.getProveedor().getId(),
                pedido.getProveedor().getNegocio(),
                pedido.getFPedido(),
                detallePedidoResponseList,
                nB);
    }

    public PedidoResponse getPedido(Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()){
            return createPedidoResponse(pedidoOptional.get());
        }else return new PedidoResponse(-1L,0L,"",null,null,1002L);
    }

    public List<PedidoResponse> getPedidoList(){
        List<Pedido> pedidoList = pedidoRepository.findAll();
        List<PedidoResponse> pedidoResponseList = new ArrayList<>();
        if(!pedidoList.isEmpty()){
            for (Pedido p: pedidoList){
                pedidoResponseList.add(createPedidoResponse(p));
            }
        }else{
            pedidoResponseList.add(new PedidoResponse(-1L,0L,"",null,null,1001L));
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
            if(dr.getCaja()<=0) b = false;
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
                detallePedido.setPedido(pedido);
                detallePedidoList.add(detallePedido);
            }

            pedido.setDetallesPedido(detallePedidoList);

            pedido = pedidoRepository.save(pedido);
            return createPedidoResponse(pedido);

        }else return new PedidoResponse(-1L,0L,"",null,null,1003L);
    }

    public PedidoResponse updatePedido(List<DetalleRequest> detalleRequestList, Long id){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        boolean b = true;

        if (pedidoOptional.isEmpty()) return new PedidoResponse(-1L,0L,"",null,null,1003L);

        for (DetalleRequest dr: detalleRequestList){
            Optional<Producto> productoOptional = productoRepository.findById(dr.getIdP());
            if(productoOptional.isEmpty()) b = false;
            if(dr.getCaja()<=0) b = false;
            if(dr.getCantidad()<=0) b = false;
        }

        if(b){
            Pedido pedido = pedidoOptional.get();
            List<detallePedido> detallePedidoList = new ArrayList<>();
            List<Long> longList = detallePedidoRepository.findByPedido(pedido.getId());
            detallePedidoRepository.deleteAllById(longList);

            for (DetalleRequest dr: detalleRequestList){
                detallePedido detallePedido = new detallePedido();
                Optional<Producto> productoOptional = productoRepository.findById(dr.getIdP());
                detallePedido.setCaja(dr.getCaja());
                detallePedido.setCantidad(dr.getCantidad());
                detallePedido.setProducto(productoOptional.get());
                detallePedido.setPedido(pedido);
                detallePedidoList.add(detallePedido);
            }

            pedido.setDetallesPedido(detallePedidoList);

            pedido = pedidoRepository.save(pedido);
            return createPedidoResponse(pedido);

        }else return new PedidoResponse(-1L,0L,"",null,null,1003L);
    }

    public List<PedidoResponse> busPedido(BusRequest busRequest){
        List<Pedido> pedidoList = pedidoRepository.findAll();


        if(busRequest.getM1()!=-1){
            List<Pedido> auxList = pedidoRepository.findByfPedidoBetween(
                    new Timestamp(busRequest.getF1().getTime()),
                    new Timestamp(busRequest.getF2().getTime())
            );
            // Convertir las listas a conjuntos para encontrar la intersección
            Set<Pedido> productosActivosSet = new HashSet<>(pedidoList);
            Set<Pedido> productosAuxSet = new HashSet<>(auxList);
            // Obtener la intersección (productos en ambas listas)
            productosActivosSet.retainAll(productosAuxSet);
            // Convertir el conjunto de vuelta a una lista (opcional)
            pedidoList = new ArrayList<>(productosActivosSet);
        }

        if(busRequest.getM2()!=-1){
            List<Pedido> auxList = pedidoRepository.findByProveedorId(Long.parseLong(busRequest.getS1()));
            // Convertir las listas a conjuntos para encontrar la intersección
            Set<Pedido> productosActivosSet = new HashSet<>(pedidoList);
            Set<Pedido> productosAuxSet = new HashSet<>(auxList);
            // Obtener la intersección (productos en ambas listas)
            productosActivosSet.retainAll(productosAuxSet);
            // Convertir el conjunto de vuelta a una lista (opcional)
            pedidoList = new ArrayList<>(productosActivosSet);
        }

        if(busRequest.getI()!=-1){
            pedidoList.removeIf(pedido -> (busRequest.isB() && boletaRepository.findByPedido(pedido).isEmpty()) ||
                    (!busRequest.isB() && boletaRepository.findByPedido(pedido).isPresent()));
        /*
            for (int i = 0; i < (long) pedidoList.size(); i++) {
                if(boletaRepository.findByPedido(pedidoList.get(i)).isEmpty() && busRequest.isB()){
                    pedidoList.remove(i);
                    i--;
                }else if(!busRequest.isB() && boletaRepository.findByPedido(pedidoList.get(i)).isPresent()){
                    pedidoList.remove(i);
                    i--;
                }
            }

        */
        }


        List<PedidoResponse> pedidoResponseList = new ArrayList<>();
        if(!pedidoList.isEmpty()){
            for (Pedido p: pedidoList){
                pedidoResponseList.add(createPedidoResponse(p));
            }
        }

        return pedidoResponseList;

    }
}
