package com.LoDeNico.Verduleria.Repository.Cuenta;

import com.LoDeNico.Verduleria.Entity.Cuenta.CuentaCorriente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CuentaCorrienteRepository extends JpaRepository<CuentaCorriente,Long> {
    @Query(value="SELECT * FROM CuentaCorriente WHERE monto BETWEEN ?1 AND ?2", nativeQuery = true)
    List<CuentaCorriente> serchByMonto(double m1, double m2);

    @Query(value="SELECT * FROM CuentaC  WHERE :tabla BETWEEN :inicio AND :cierre", nativeQuery = true)
    List< CuentaCorriente > findByDias(@Param("inicio") Timestamp inicio, @Param("cierre") Timestamp cierre, @Param("tabla") Timestamp tabla);

}
