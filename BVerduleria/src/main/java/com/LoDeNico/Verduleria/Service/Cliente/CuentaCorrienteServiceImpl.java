package com.LoDeNico.Verduleria.Service.Cliente;

import com.LoDeNico.Verduleria.Repository.Cuenta.CuentaCorrienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaCorrienteServiceImpl implements ClienteService{
    @Autowired
    private final CuentaCorrienteRepository cuentaCorrienteRepository;

    public CuentaCorrienteServiceImpl(CuentaCorrienteRepository cuentaCorrienteRepository) {
        this.cuentaCorrienteRepository = cuentaCorrienteRepository;
    }




}
