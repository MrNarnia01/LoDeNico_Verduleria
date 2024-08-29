package com.LoDeNico.Verduleria.Repository.Detalle;

import com.LoDeNico.Verduleria.Entity.Detalle.detalleBoleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleBoletaRepository extends JpaRepository<detalleBoleta,Long> {

}
