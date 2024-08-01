package com.LoDeNico.Verduleria.Service.Cierre;

import com.LoDeNico.Verduleria.Dto.Request.Cierre.CierreRequest;
import com.LoDeNico.Verduleria.Dto.Request.FechaRequest;
import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import com.LoDeNico.Verduleria.Entity.Cierre.CierreLote;
import com.LoDeNico.Verduleria.Repository.Cierre.CierreLoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CierreLoteLoteServiceImpl implements CierreLoteService {
    @Autowired
    private final CierreLoteRepository cierreLoteRepository;

    public CierreLoteLoteServiceImpl(CierreLoteRepository cierreLoteRepository) {
        this.cierreLoteRepository = cierreLoteRepository;
    }

    public CierreLote createCierre(CierreRequest cierreRequest){
        boolean b = true;

        if(cierreRequest.getMonto()<=0) b = false;

        if(b){
            CierreLote cierreLote =  new CierreLote();
            cierreLote.setMonto(cierreRequest.getMonto());
            cierreLote.setTipo(cierreLote.isTipo());
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

    /*
        Codigos de busqueda:
            1 : Solo tipo
            2 : Ambos
            3 : Solo monto
     */

    public List<CierreLote> cierreLoteBus(FechaRequest fechaRequest, boolean t, int cod, MontoRequest montoRequest){
        List<CierreLote> cierreLoteList = new ArrayList<>();
        //Darle datos a la lista
        if(fechaRequest.getF1() == null){
            cierreLoteList = cierreLoteRepository.findAll();
        }else{
            cierreLoteList = cierreLoteRepository.findByDias(fechaRequest.getF1(),fechaRequest.getF2());
        }

        //Datos tipo
        if(cod<=2){
            List<CierreLote> cierreTipo = cierreLoteRepository.findByTipo(t);
            //Filtracion
            for (int i = 0; i < cierreLoteList.stream().count(); i++) {
                boolean b = false;
                for (int j = 0; j < cierreTipo.stream().count(); j++) {
                    if (cierreLoteList.get(i).equals(cierreTipo.get(j)))    b=true;
                }
                if(!b){
                    cierreLoteList.remove(i);
                    i--;
                }
            }
        }
        //Datos monto
        if(cod>=2){
            List<CierreLote> cierreMonto = cierreLoteRepository.serchByMonto(montoRequest.getM1(), montoRequest.getM2());
            //Filtracion
            for (int i = 0; i < cierreLoteList.stream().count(); i++) {
                boolean b = false;
                for (int j = 0; j < cierreMonto.stream().count(); j++) {
                    if (cierreLoteList.get(i).equals(cierreMonto.get(j)))    b=true;
                }
                if(!b)  cierreLoteList.remove(i);
                i--;
            }
        }
        return cierreLoteList;
    }




}
