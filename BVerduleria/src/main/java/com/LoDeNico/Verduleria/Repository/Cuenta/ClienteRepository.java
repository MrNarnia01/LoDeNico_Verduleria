package com.LoDeNico.Verduleria.Repository.Cuenta;

import com.LoDeNico.Verduleria.Entity.Cuenta.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByCalle(String calle);
}
