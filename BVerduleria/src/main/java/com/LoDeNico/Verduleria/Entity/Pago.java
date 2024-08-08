package com.LoDeNico.Verduleria.Entity;

import com.LoDeNico.Verduleria.Entity.Producto.Boleta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "nBoleta")
    private Boleta boleta;

    @Column(name = "tipo")
    private String tipo;

    @CreationTimestamp
    @Column(name = "fPago")
    private Timestamp fPago;

    @Column(name = "monto")
    private double monto;

}
