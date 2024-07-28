package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.Detalle.DetalleRequest;
import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.BoletaRequest;
import com.LoDeNico.Verduleria.Dto.Response.Detalle.DetalleBoletaResponse;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;
import com.LoDeNico.Verduleria.Dto.Response.Producto.BoletaResponse;
import com.LoDeNico.Verduleria.Entity.Detalle.detalleBoleta;
import com.LoDeNico.Verduleria.Entity.Detalle.detallePedido;
import com.LoDeNico.Verduleria.Entity.Pago;
import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import com.LoDeNico.Verduleria.Repository.Producto.BoletaRepository;
import com.LoDeNico.Verduleria.Repository.Producto.PedidoRepository;
import com.LoDeNico.Verduleria.Repository.Producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoletaServiceImpl implements BoletaService{
    @Autowired
    private final BoletaRepository boletaRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public BoletaServiceImpl(BoletaRepository boletaRepository, PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.boletaRepository = boletaRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    private BoletaResponse createBoletaResponse(Boleta boleta){
        List<DetalleBoletaResponse> detalleBoletaResponseList = new ArrayList<>();
        List<PagoResponse> pagoResponseList = new ArrayList<>();

        for (detalleBoleta db: boleta.getDetallesBoleta()){
            detalleBoletaResponseList.add(new DetalleBoletaResponse(
                    db.getId(),
                    db.getBoleta().getId(),
                    db.getProducto().getNombre(),
                    db.getProducto().isUnit(),
                    db.getCaja(),
                    db.getCantidad()
            ));
        }

        for (Pago p: boleta.getPagos()){
            pagoResponseList.add(new PagoResponse(
                    p.getId(),
                    p.getBoleta().getId(),
                    p.getTipo(),
                    p.getFPago(),
                    p.getMonto()
            ));
        }

        if (pagoResponseList.isEmpty()) pagoResponseList = null;

        return new BoletaResponse(
                boleta.getId(),
                boleta.getPedido().getId(),
                boleta.isPaga(),
                boleta.getFRecibo(),
                boleta.getMonto(),
                detalleBoletaResponseList,
                pagoResponseList
        );

    }

    public BoletaResponse getBoleta(Long id){
        Optional<Boleta> boletaOptional = boletaRepository.findById(id);
        if(boletaOptional.isPresent()){
            return createBoletaResponse(boletaOptional.get());
        }else{
            return new BoletaResponse(-1L,1002L,false,null,0,null,null);
        }
    }

    public List<BoletaResponse> getBoletaList(){
        List<Boleta> boletaList = boletaRepository.findAll();
        List<BoletaResponse> boletaResponseList = new ArrayList<>();
        if(!boletaList.isEmpty()){
            for (Boleta b: boletaList){
                boletaResponseList.add(createBoletaResponse(b));
            }
        }else{
            boletaResponseList.add(new BoletaResponse(-1L,1001L,false,null,0,null,null));
        }
        return boletaResponseList;
    }

    public int deleteBoleta(Long id){
        Optional<Boleta> boletaOptional = boletaRepository.findById(id);
        if(boletaOptional.isPresent()){
            boletaRepository.deleteById(id);
            return 0;
        }else{
            return 1002;
        }
    }

    public List<BoletaResponse> getBoletaListByMonto(MontoRequest montoRequest){
        List<Boleta> boletaList = boletaRepository.serchByMonto(montoRequest.getM1(), montoRequest.getM2());
        List<BoletaResponse> boletaResponseList = new ArrayList<>();
        if(!boletaList.isEmpty()){
            for (Boleta b: boletaList){
                boletaResponseList.add(createBoletaResponse(b));
            }
        }else{
            boletaResponseList.add(new BoletaResponse(-1L,1001L,false,null,0,null,null));
        }
        return boletaResponseList;
    }

    public List<BoletaResponse> getBoletaListByPago(boolean pago){
        List<Boleta> boletaList = boletaRepository.findByPaga(pago);
        List<BoletaResponse> boletaResponseList = new ArrayList<>();
        if(!boletaList.isEmpty()){
            for (Boleta b: boletaList){
                boletaResponseList.add(createBoletaResponse(b));
            }
        }else{
            boletaResponseList.add(new BoletaResponse(-1L,1001L,false,null,0,null,null));
        }
        return boletaResponseList;
    }

    public BoletaResponse createBoleta(BoletaRequest boletaRequest){
        boolean b = true;

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(boletaRequest.getIdP());

        if (boletaRequest.getNB()<0) b = false;
        if (pedidoOptional.isEmpty()) b = false;
        for (DetalleRequest br: boletaRequest.getDetalleBoletaRequestList()){
            Optional<Producto> productoOptional = productoRepository.findById(br.getIdP());
            if(productoOptional.isEmpty()) b = false;
            if(br.getCaja()<0) b = false;
            if(br.getCantidad()<=0) b = false;
        }

        if (b){
            Boleta boleta = new Boleta();
            boleta.setId(boletaRequest.getNB());
            boleta.setPedido(pedidoOptional.get());
            boleta.setPaga(false);
            boleta.setMonto(boletaRequest.getMonto());

            List<detalleBoleta> detalleBoletaList = new ArrayList<>();
            for (DetalleRequest br: boletaRequest.getDetalleBoletaRequestList()){
                detalleBoleta detalleBoleta = new detalleBoleta();
                detalleBoleta.setCantidad(br.getCantidad());
                detalleBoleta.setCaja(br.getCaja());
                Optional<Producto> productoOptional = productoRepository.findById(br.getIdP());
                detalleBoleta.setProducto(productoOptional.get());
                detalleBoletaList.add(detalleBoleta);
            }

            boleta.setDetallesBoleta(detalleBoletaList);

            boleta = boletaRepository.save(boleta);
            return createBoletaResponse(boleta);

        }else{
            return  new BoletaResponse(-1L,1003L,false,null,0,null,null);
        }
    }

    public BoletaResponse updateBoleta(BoletaRequest boletaRequest){
        boolean b = true;

        Optional<Boleta> boletaOptional = boletaRepository.findById(boletaRequest.getNB());
        if(boletaOptional.isEmpty())    return  new BoletaResponse(-1L,1002L,false,null,0,null,null);

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(boletaRequest.getIdP());

        if (boletaRequest.getNB()<0) b = false;
        if (pedidoOptional.isEmpty()) b = false;
        for (DetalleRequest br: boletaRequest.getDetalleBoletaRequestList()){
            Optional<Producto> productoOptional = productoRepository.findById(br.getIdP());
            if(productoOptional.isEmpty()) b = false;
            if(br.getCaja()<0) b = false;
            if(br.getCantidad()<=0) b = false;
        }

        if (b){
            Boleta boleta = boletaOptional.get();
            boleta.setPedido(pedidoOptional.get());
            boleta.setPaga(false);
            boleta.setMonto(boletaRequest.getMonto());

            List<detalleBoleta> detalleBoletaList = new ArrayList<>();
            for (DetalleRequest br: boletaRequest.getDetalleBoletaRequestList()){
                detalleBoleta detalleBoleta = new detalleBoleta();
                detalleBoleta.setCantidad(br.getCantidad());
                detalleBoleta.setCaja(br.getCaja());
                Optional<Producto> productoOptional = productoRepository.findById(br.getIdP());
                detalleBoleta.setProducto(productoOptional.get());
                detalleBoletaList.add(detalleBoleta);
            }

            boleta.setDetallesBoleta(detalleBoletaList);

            boleta = boletaRepository.save(boleta);
            return createBoletaResponse(boleta);

        }else{
            return  new BoletaResponse(-1L,1003L,false,null,0,null,null);
        }
    }

}
