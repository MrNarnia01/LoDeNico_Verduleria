package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.Producto.BusPRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.ProductoRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.LoteResponse;
import com.LoDeNico.Verduleria.Dto.Response.Producto.ProductoResponse;
import com.LoDeNico.Verduleria.Entity.Producto.Lote;
import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import com.LoDeNico.Verduleria.Repository.Producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private final ProductoRepository productoRepository;


    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ProductoResponse createProductoResponse(Producto producto){
        ProductoResponse productoResponse =  new ProductoResponse();

        productoResponse.setId(producto.getId());
        productoResponse.setNombre(producto.getNombre());
        productoResponse.setUnit(producto.isUnit());
        productoResponse.setStock(producto.getStock());
        productoResponse.setPrecio(producto.getPrecio());

        List<LoteResponse> loteResponseList=  new ArrayList<>();
        for (Lote lote: producto.getLotes()){
            LoteResponse loteResponse = new LoteResponse();
            loteResponse.setId(lote.getId());
            loteResponse.setNLote(lote.getNLote());
            loteResponse.setFVenci(lote.getFVenci());
            loteResponse.setNombre(lote.getProducto().getNombre());
            loteResponseList.add(loteResponse);
        }

        productoResponse.setLoteResponseList(loteResponseList);
        return productoResponse;
    }

    public ProductoResponse getProducto(Long id){
        Optional<Producto> productoOptional = productoRepository.findById(id);

        if(productoOptional.isPresent()){
            return createProductoResponse(productoOptional.get());
        }else{
            return new ProductoResponse(-1L,"",0,1002,true,null);
        }
    }

    public List<ProductoResponse> getProductoList(boolean pl){
        List<Producto> productoList = productoRepository.findBySoftDelete(pl);
        List<ProductoResponse> productoResponseList = new ArrayList<>();
        if(!productoList.isEmpty()){
            for (Producto p: productoList){
                productoResponseList.add(createProductoResponse(p));
            }
        }else{
            ProductoResponse productoResponse = new ProductoResponse(-1L,"",0,1001,true,null);
            productoResponseList.add(productoResponse);
        }
        return productoResponseList;
    }

    public int deleteProducto(Long id){
        Optional<Producto> productoOptional = productoRepository.findById(id);

        if(productoOptional.isPresent()){
            Producto producto = productoOptional.get();
            producto.setSoftDelete(true);
            productoRepository.save(producto);
            return 0;
        }else{
            return 1002;
        }
    }

    public ProductoResponse createProducto(ProductoRequest productoRequest){
        boolean b = true;

        Optional<Producto> productoOptional = productoRepository.findByNombre(productoRequest.getNombre());
        if (productoOptional.isPresent())   b = false;
        if (productoRequest.getNombre().isBlank())  b = false;
        if (productoRequest.getPrecio()<=0) b = false;

        if(b){
            Producto producto = new Producto();
            producto.setNombre(productoRequest.getNombre());
            producto.setPrecio(productoRequest.getPrecio());
            producto.setStock(0);
            producto.setUnit(productoRequest.isUnit());
            producto.setSoftDelete(false);
            producto.setLotes(new ArrayList<>());
            producto = productoRepository.save(producto);
            return createProductoResponse(producto);
        }else{
            return new ProductoResponse(-1L,"",0,1003,true,null);
        }
    }

    public ProductoResponse updateProducto(ProductoRequest productoRequest, Long id){
        boolean b = true;
        Producto producto = new Producto();

        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isEmpty()){
            return new ProductoResponse(-1L,"",0,1002,true,null);
        }else producto = productoOptional.get();

        productoOptional = productoRepository.findByNombre(productoRequest.getNombre());
        if (productoOptional.isEmpty())   b = false;
        if (productoRequest.getNombre().isBlank())  b = false;
        if (productoRequest.getPrecio()<=0) b = false;

        if(b){
            producto.setNombre(productoRequest.getNombre());
            producto.setPrecio(productoRequest.getPrecio());
            producto.setUnit(productoRequest.isUnit());
            producto = productoRepository.save(producto);
            return createProductoResponse(producto);
        }else{
            return new ProductoResponse(-1L,"",0,1003,true,null);
        }
    }

    public int updateStock(Long id, int n){
        Optional<Producto> productoOptional = productoRepository.findById(id);

        if(productoOptional.isPresent() && n>0){
            Producto producto = productoOptional.get();
            producto.addP(n);
            productoRepository.save(producto);
            return 0;
        }

        return 1002;
    }

    public List<ProductoResponse> busProducto(BusPRequest busPRequest){
        List<Producto> productoList = new ArrayList<>();
        if(busPRequest.getNombre()==null)    productoList = productoRepository.findBySoftDelete(false);
        else productoList = productoRepository.serchByNombre(busPRequest.getNombre());

        if(busPRequest.getUni() !=0){
            boolean u = true;
            if(busPRequest.getUni() == 2)    u = false;

            for (int i = 0; i < productoList.stream().count(); i++) {
                if(productoList.get(i).isUnit()!=u){
                    productoList.remove(i);
                    i--;
                }
            }
        }

        if(busPRequest.getMontoRequest().getM1()!=-1){
            List<Producto> montoList = productoRepository.serchByPrecio(busPRequest.getMontoRequest().getM1(), busPRequest.getMontoRequest().getM2());

            for (int i = 0; i < productoList.stream().count(); i++) {
                boolean b = false;
                for (int j = 0; j < montoList.stream().count(); j++) {
                    if (productoList.get(i).equals(montoList.get(j)))    b=true;
                }
                if(!b){
                    productoList.remove(i);
                    i--;
                }
            }

        }

        List<ProductoResponse> productoResponseList = new ArrayList<>();
        for (Producto p: productoList){
            productoResponseList.add(createProductoResponse(p));
        }

        return productoResponseList;
    }

}
