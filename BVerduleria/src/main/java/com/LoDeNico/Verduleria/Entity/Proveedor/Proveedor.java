package com.LoDeNico.Verduleria.Entity.Proveedor;

import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
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
@Table(name = "Proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "negocio")
    private String negocio;

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private int altura;

    @OneToMany(mappedBy = "proveedor")
    private List<Trabajador> trabajadores;

    @OneToMany(mappedBy = "proveedor")
    private List<Pedido> pedidos;

}
