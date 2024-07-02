package com.LoDeNico.Verduleria.Entity.Producto;

import com.LoDeNico.Verduleria.Entity.Detalle.detallePedido;
import com.LoDeNico.Verduleria.Entity.Proveedor.Proveedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

    @Column(name = "fPedido")
    private Date fPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<detallePedido> detallesPedido;

    @OneToOne(mappedBy = "pedido")
    private Boleta boleta;

}
