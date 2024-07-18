package com.LoDeNico.Verduleria.Entity.Producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private double precio;

    @Column(name = "stock")
    private int stock;

    @Column(name = "unit")
    private boolean unit;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Lote> lotes;

    @Column(name = "softDelete")
    private boolean softDelete;
}
