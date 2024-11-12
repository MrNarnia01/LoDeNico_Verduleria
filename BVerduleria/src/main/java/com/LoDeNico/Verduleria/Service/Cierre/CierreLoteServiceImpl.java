package com.LoDeNico.Verduleria.Service.Cierre;

import com.LoDeNico.Verduleria.Dto.Request.BusRequest;
import com.LoDeNico.Verduleria.Dto.Request.Cierre.CierreRequest;
import com.LoDeNico.Verduleria.Entity.Cierre.CierreLote;
import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import com.LoDeNico.Verduleria.Repository.Cierre.CierreLoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class CierreLoteServiceImpl implements CierreLoteService {
    @Autowired
    private final CierreLoteRepository cierreLoteRepository;

    public CierreLoteServiceImpl(CierreLoteRepository cierreLoteRepository) {
        this.cierreLoteRepository = cierreLoteRepository;
    }

    public CierreLote createCierre(CierreRequest cierreRequest){
        boolean b = true;

        if(cierreRequest.getMonto()<=0) b = false;

        if(b){
            CierreLote cierreLote =  new CierreLote();
            cierreLote.setMonto(cierreRequest.getMonto());
            cierreLote.setTipo(cierreRequest.isTipo());
            cierreLote = cierreLoteRepository.save(cierreLote);
            return cierreLote;
        }else{
            return new CierreLote(-1L,true,1003,null);
        }
    }

    public int deleteCierre(Long id){
        Optional<CierreLote> cierreLoteOptional = cierreLoteRepository.findById(id);
        if(cierreLoteOptional.isPresent()){
            cierreLoteRepository.deleteById(id);
            return 0;
        }else{
            return 1002;
        }
    }

    public List<CierreLote> cierreLoteBus(BusRequest busRequest){
        List<CierreLote> cierreLoteList = cierreLoteRepository.findAll();

        if(busRequest.getM1()!=-1){
            List<CierreLote> auxList = cierreLoteRepository.findByfRegisBetween(
                    new Timestamp(busRequest.getF1().getTime()),new Timestamp(busRequest.getF2().getTime()));
            // Convertir las listas a conjuntos para encontrar la intersección
            Set<CierreLote> productosActivosSet = new HashSet<>(cierreLoteList);
            Set<CierreLote> productosAuxSet = new HashSet<>(auxList);
            // Obtener la intersección (productos en ambas listas)
            productosActivosSet.retainAll(productosAuxSet);
            // Convertir el conjunto de vuelta a una lista (opcional)
            cierreLoteList = new ArrayList<>(productosActivosSet);
        }

        if(busRequest.getI()!=-1){
            List<CierreLote> auxList = cierreLoteRepository.findByTipo(busRequest.isB());
            // Convertir las listas a conjuntos para encontrar la intersección
            Set<CierreLote> productosActivosSet = new HashSet<>(cierreLoteList);
            Set<CierreLote> productosAuxSet = new HashSet<>(auxList);
            // Obtener la intersección (productos en ambas listas)
            productosActivosSet.retainAll(productosAuxSet);
            // Convertir el conjunto de vuelta a una lista (opcional)
            cierreLoteList = new ArrayList<>(productosActivosSet);
        }
        return cierreLoteList;
    }

    public List<CierreLote> cierreLoteList(){
        return cierreLoteRepository.findAll();
    }

    public CierreLote updateCierre(CierreRequest cierreRequest, Long id){
        boolean b = true;
        Optional<CierreLote> cierreLoteOptional = cierreLoteRepository.findById(id);
        if(cierreLoteOptional.isEmpty())    return new CierreLote(-1L,true,1002,null);
        if(cierreRequest.getMonto()<=0) b = false;

        if(b){
            CierreLote cierreLote =  cierreLoteOptional.get();
            cierreLote.setMonto(cierreRequest.getMonto());
            cierreLote.setTipo(cierreRequest.isTipo());
            cierreLote = cierreLoteRepository.save(cierreLote);
            return cierreLote;
        }else{
            return new CierreLote(-1L,true,1003,null);
        }
    }



}
