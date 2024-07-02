package com.LoDeNico.Verduleria.Entity.Cierre;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cierre")
public class CierreLote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tipo")
    private boolean tipo;

    @Column(name = "monto")
    private double monto;

    @Column(name = "fRegis")
    private Date fRegis;

}
