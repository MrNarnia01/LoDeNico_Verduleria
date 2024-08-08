package com.LoDeNico.Verduleria.Entity.Cuenta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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

    @CreationTimestamp
    @Column(name = "fRegistro")
    private Timestamp fRegistro;

    @CreationTimestamp
    @Column(name = "fPago")
    private Timestamp fPago;

}
