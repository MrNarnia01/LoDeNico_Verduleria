package com.LoDeNico.Verduleria.Entity.Cuenta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "CuentaC")
public class CuentaCorriente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Column(name = "monto")
    private double monto;

    @Column(name = "fRegistro")
    private Date fRegistro;

    @Column(name = "fPago")
    private Date fPago;

}
