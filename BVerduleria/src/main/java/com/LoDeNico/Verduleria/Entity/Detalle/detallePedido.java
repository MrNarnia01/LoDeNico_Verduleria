package com.LoDeNico.Verduleria.Entity.Detalle;

import com.LoDeNico.Verduleria.Entity.Producto.Pedido;
import com.LoDeNico.Verduleria.Entity.Producto.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DetallePedido")
public class detallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @Column(name = "caja")
    private int caja;

    @Column(name = "cantidad")
    private int cantidad;

}
