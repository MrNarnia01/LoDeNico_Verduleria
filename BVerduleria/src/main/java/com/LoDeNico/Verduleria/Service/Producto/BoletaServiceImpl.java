package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.BusRequest;
import com.LoDeNico.Verduleria.Dto.Request.Detalle.DetalleRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.BoletaRequest;
import com.LoDeNico.Verduleria.Dto.Response.Detalle.DetalleBoletaResponse;
import com.LoDeNico.Verduleria.Dto.Response.PagoResponse;
import com.LoDeNico.Verduleria.Dto.Response.Producto.BoletaResponse;
import com.LoDeNico.Verduleria.Entity.Detalle.detalleBoleta;
import com.LoDeNico.Verduleria.Entity.Pago;
import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import com.LoDeNico.Verduleria.Repository.Detalle.DetalleBoletaRepository;
import com.LoDeNico.Verduleria.Repository.Producto.BoletaRepository;
import com.LoDeNico.Verduleria.Repository.Producto.PedidoRepository;
import com.LoDeNico.Verduleria.Repository.Producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class BoletaServiceImpl implements BoletaService{
    @Autowired
    private final BoletaRepository boletaRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final DetalleBoletaRepository detalleBoletaRepository;

    public BoletaServiceImpl(BoletaRepository boletaRepository, PedidoRepository pedidoRepository, ProductoRepository productoRepository, DetalleBoletaRepository detalleBoletaRepository) {
        this.boletaRepository = boletaRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.detalleBoletaRepository = detalleBoletaRepository;
    }

    private BoletaResponse createBoletaResponse(Boleta boleta){
        List<DetalleBoletaResponse> detalleBoletaResponseList = new ArrayList<>();
        List<PagoResponse> pagoResponseList = new ArrayList<>();

        for (detalleBoleta db: boleta.getDetallesBoleta()){
            detalleBoletaResponseList.add(new DetalleBoletaResponse(
                    db.getId(),
                    db.getBoleta().getId(),
                    db.getProducto().getNombre(),
                    db.getProducto().getId(),
                    db.getCaja(),
                    db.getCantidad()
            ));
        }
        if(!boleta.getPagos().isEmpty()) {
            for (Pago p : boleta.getPagos()) {
                pagoResponseList.add(new PagoResponse(
                        p.getId(),
                        p.getBoleta().getId(),
                        p.getTipo(),
                        p.getFPago(),
                        p.getMonto()
                ));
            }
        }

        return new BoletaResponse(
                boleta.getId(),
                boleta.getNB(),
                boleta.getPedido().getId(),
                boleta.getPedido().getProveedor().getNegocio(),
                boleta.isPaga(),
                boleta.getFRecibo(),
                boleta.getMonto(),
                boleta.allPagado(),
                detalleBoletaResponseList,
                pagoResponseList
        );

    }

    public BoletaResponse getBoleta(Long id){
        Optional<Boleta> boletaOptional = boletaRepository.findById(id);
        if(boletaOptional.isPresent()){
            return createBoletaResponse(boletaOptional.get());
        }else{
            return new BoletaResponse(0L,-1L,1002L,"",false,null,0,0,null,null);
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
            boletaResponseList.add(new BoletaResponse(0L,-1L,1001L,"",false,null,0,0,null,null));
        }
        return boletaResponseList;
    }

    public int deleteBoleta(Long id){
        Optional<Boleta> boletaOptional = boletaRepository.findById(id);
        if(boletaOptional.isPresent()){
            Boleta boleta = boletaOptional.get();
            List<detalleBoleta> detalleBoletaList = new ArrayList<>();
            //Borrar Stock
            for (detalleBoleta db: boleta.getDetallesBoleta()){
                db.getProducto().addP( -(db.getCaja() * db.getCantidad()) );
                if(db.getProducto().getStock()<0){
                    db.getProducto().setStock(0);
                }
                detalleBoletaList.add(db);
            }
            boleta.setDetallesBoleta(detalleBoletaList);
            boleta = boletaRepository.save(boleta);
            boletaRepository.deleteById(id);
            return 0;
        }else{
            return 1002;
        }
    }

    public List<BoletaResponse> getBoletaBus(BusRequest busRequest){
        List<Boleta> boletaList = boletaRepository.findAll();

        if(busRequest.getM1()!=-1){
            List<Boleta> auxList = boletaRepository.findByfReciboBetween(
                    new Timestamp(busRequest.getF1().getTime()),
                    new Timestamp(busRequest.getF2().getTime())
            );
            // Convertir las listas a conjuntos para encontrar la intersección
            Set<Boleta> productosActivosSet = new HashSet<>(boletaList);
            Set<Boleta> productosAuxSet = new HashSet<>(auxList);
            // Obtener la intersección (productos en ambas listas)
            productosActivosSet.retainAll(productosAuxSet);
            // Convertir el conjunto de vuelta a una lista (opcional)
            boletaList = new ArrayList<>(productosActivosSet);
        }

        if(busRequest.getI()!=-1){
            boletaList.removeIf(boleta -> (busRequest.isB()) && !boleta.isPaga() ||
                    !busRequest.isB() && boleta.isPaga());
        }

        List<BoletaResponse> boletaResponseList = new ArrayList<>();
        if(!boletaList.isEmpty()){
            for (Boleta bo: boletaList){
                boletaResponseList.add(createBoletaResponse(bo));
            }
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
            boletaResponseList.add(new BoletaResponse(0L,-1L,1001L,"",false,null,0,0,null,null));
        }
        return boletaResponseList;
    }

    public BoletaResponse createBoleta(BoletaRequest boletaRequest){
        boolean b = true;

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(boletaRequest.getIdP());

        if (boletaRequest.getNumB()<0) b = false;
        if (pedidoOptional.isEmpty()) b = false;
        for (DetalleRequest br: boletaRequest.getDetalleBoletaRequestList()){
            Optional<Producto> productoOptional = productoRepository.findById(br.getIdP());
            if(productoOptional.isEmpty()) b = false;
            if(br.getCaja()<=0) b = false;
            if(br.getCantidad()<=0) b = false;
        }

        if (b){
            Boleta boleta = new Boleta();
            boleta.setNB(boletaRequest.getNumB());
            boleta.setPedido(pedidoOptional.get());
            boleta.setPaga(false);
            boleta.setMonto(boletaRequest.getMonto());

            List<detalleBoleta> detalleBoletaList = new ArrayList<>();
            for (DetalleRequest br: boletaRequest.getDetalleBoletaRequestList()){
                detalleBoleta detalleBoleta = new detalleBoleta();
                detalleBoleta.setCantidad(br.getCantidad());
                detalleBoleta.setCaja(br.getCaja());
                Optional<Producto> productoOptional = productoRepository.findById(br.getIdP());
                Producto producto = productoOptional.get();
                producto.addP(detalleBoleta.getCaja() * detalleBoleta.getCantidad() );
                detalleBoleta.setProducto(producto);
                detalleBoleta.setBoleta(boleta);
                detalleBoletaList.add(detalleBoleta);
            }

            boleta.setDetallesBoleta(detalleBoletaList);
            List<Pago> pagoList = new ArrayList<>();
            boleta.setPagos(pagoList);


            boleta = boletaRepository.save(boleta);
            return createBoletaResponse(boleta);

        }else{
            return  new BoletaResponse(0L,-1L,1003L,"",false,null,0,0,null,null);
        }
    }

    public BoletaResponse updateBoleta(BoletaRequest boletaRequest, Long id){
        boolean b = true;

        Optional<Boleta> boletaOptional = boletaRepository.findById(id);
        if(boletaOptional.isEmpty())    return  new BoletaResponse(0L,-1L,1002L,"",false,null,0,0,null,null);

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(boletaRequest.getIdP());

        if (boletaRequest.getNumB()<0) b = false;
        if (pedidoOptional.isEmpty()) b = false;
        for (DetalleRequest br: boletaRequest.getDetalleBoletaRequestList()){
            Optional<Producto> productoOptional = productoRepository.findById(br.getIdP());
            if(productoOptional.isEmpty()) b = false;
            if(br.getCaja()<=0) b = false;
            if(br.getCantidad()<=0) b = false;
        }

        if (b){
            Boleta boleta = boletaOptional.get();
            List<detalleBoleta> detalleBoletaList = new ArrayList<>();
            //Borrar Stock
            for (detalleBoleta db: boleta.getDetallesBoleta()){
                db.getProducto().addP( -(db.getCaja() * db.getCantidad()) );
                if(db.getProducto().getStock()<0){
                    db.getProducto().setStock(0);
                }
                detalleBoletaList.add(db);
            }
            boleta.setDetallesBoleta(detalleBoletaList);
            boleta.setMonto(boletaRequest.getMonto());
            boleta = boletaRepository.save(boleta);

            detalleBoletaRepository.deleteByBoleta(boleta.getId());

            //Boleta update
            boleta.setNB(boletaRequest.getNumB());
            detalleBoletaList = new ArrayList<>();
            if(boleta.allPagado()!=boleta.getMonto()){
                boleta.setMonto(boletaRequest.getMonto());
            }

            for (DetalleRequest br: boletaRequest.getDetalleBoletaRequestList()){
                detalleBoleta detalleBoleta = new detalleBoleta();
                detalleBoleta.setCantidad(br.getCantidad());
                detalleBoleta.setCaja(br.getCaja());
                Optional<Producto> productoOptional = productoRepository.findById(br.getIdP());
                Producto producto = productoOptional.get();
                producto.addP(detalleBoleta.getCaja() * detalleBoleta.getCantidad() );
                detalleBoleta.setProducto(producto);
                detalleBoleta.setBoleta(boleta);
                detalleBoletaList.add(detalleBoleta);
            }

            boleta.setDetallesBoleta(detalleBoletaList);

            boleta = boletaRepository.save(boleta);
            return createBoletaResponse(boleta);

        }else{
            return  new BoletaResponse(0L,-1L,1003L,"",false,null,0,0,null,null);
        }
    }

}
