package com.LoDeNico.Verduleria.Repository.Cuenta;

import com.LoDeNico.Verduleria.Entity.Cuenta.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByCalle(String calle);
}
