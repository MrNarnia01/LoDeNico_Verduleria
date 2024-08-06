package com.LoDeNico.Verduleria.Entity.Producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Lote")
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @Column(name = "nLote")
    private int nLote;

    @Column(name = "fVenci")
    private Timestamp fVenci;

}
