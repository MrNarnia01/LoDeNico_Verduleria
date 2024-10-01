package com.LoDeNico.Verduleria.Service.Producto;

import com.LoDeNico.Verduleria.Dto.Request.FechaRequest;
import com.LoDeNico.Verduleria.Dto.Request.Producto.LoteRequest;
import com.LoDeNico.Verduleria.Dto.Response.Producto.LoteResponse;
import com.LoDeNico.Verduleria.Entity.Producto.Lote;
import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import com.LoDeNico.Verduleria.Repository.Producto.LoteRepository;
import com.LoDeNico.Verduleria.Repository.Producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoteServiceImpl implements LoteService{
    @Autowired
    private final LoteRepository loteRepository;
    private final ProductoRepository productoRepository;

    public LoteServiceImpl(LoteRepository loteRepository, ProductoRepository productoRepository) {
        this.loteRepository = loteRepository;
        this.productoRepository = productoRepository;
    }

    private LoteResponse createLoteResponse(Lote lote){
        LoteResponse loteResponse = new LoteResponse();
        loteResponse.setId(lote.getId());
        loteResponse.setNLote(lote.getNLote());
        loteResponse.setFVenci(lote.getFVenci());
        loteResponse.setNombre(lote.getProducto().getNombre());
        return loteResponse;
    }

    public LoteResponse getLote(Long id){
        Optional<Lote> loteOptional = loteRepository.findById(id);
        if(loteOptional.isPresent()){
            Lote lote = loteOptional.get();
            return createLoteResponse(lote);
        }else return new LoteResponse(-1L,"",1002,null);
    }

    public List<LoteResponse> getLoteList(){
        List<Lote> loteList = loteRepository.findAll();
        List<LoteResponse> loteResponseList = new ArrayList<>();
        if(!loteList.isEmpty()){
            for(Lote l: loteList){
                LoteResponse loteResponse = createLoteResponse(l);
                loteResponseList.add(loteResponse);
            }
            return loteResponseList;
        }else{
            LoteResponse loteResponse = new LoteResponse(-1L,"",1001,null);
            loteResponseList.add(loteResponse);
            return loteResponseList;
        }
    }

    public int deleteLote(Long id){
        Optional<Lote> loteOptional = loteRepository.findById(id);
        if (loteOptional.isPresent()){
            loteRepository.deleteById(id);
            return 0;
        }else{
            return 1002;
        }
    }

    public LoteResponse createLote(LoteRequest loteRequest){
        boolean b = true;
        Optional<Producto> productoOptional = productoRepository.findById(loteRequest.getIdP());
        if(productoOptional.isEmpty()) b = false;
        if(loteRequest.getNum()<=0) b = false;

        if(b){
            Lote lote = new Lote();
            lote.setProducto(productoOptional.get());
            lote.setNLote(loteRequest.getNum());
            lote.setFVenci(new Timestamp(loteRequest.getDate().getTime()));
            lote = loteRepository.save(lote);

            return createLoteResponse(lote);
        }else   return new LoteResponse(-1L,"",1003,null);
    }

    public LoteResponse updateLote(LoteRequest loteRequest, Long id){
        boolean b = true;
        Optional<Lote> loteOptional = loteRepository.findById(id);
        if (loteOptional.isEmpty()) return new LoteResponse(-1L,"",1002,null);

        Optional<Producto> productoOptional = productoRepository.findById(loteRequest.getIdP());

        if(productoOptional.isEmpty()) b = false;
        if(loteRequest.getNum()<=0) b = false;

        if(b){
            Lote lote = loteOptional.get();
            lote.setProducto(productoOptional.get());
            lote.setNLote(loteRequest.getNum());
            lote.setFVenci(new Timestamp(loteRequest.getDate().getTime()));
            lote = loteRepository.save(lote);

            return createLoteResponse(lote);
        }else   return new LoteResponse(-1L,"",1003,null);
    }

    public List<LoteResponse> getLoteListByDias(FechaRequest fechaRequest){
        List<Lote> loteList = loteRepository.findByDias(fechaRequest.getF1(),fechaRequest.getF2());
        List<LoteResponse> loteResponseList = new ArrayList<>();
        if(!loteList.isEmpty()){
            for(Lote l: loteList){
                LoteResponse loteResponse = createLoteResponse(l);
                loteResponseList.add(loteResponse);
            }
            return loteResponseList;
        }else{
            LoteResponse loteResponse = new LoteResponse(-1L,"",1001,null);
            loteResponseList.add(loteResponse);
            return loteResponseList;
        }
    }


}
