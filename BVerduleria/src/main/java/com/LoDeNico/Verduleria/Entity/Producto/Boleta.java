package com.LoDeNico.Verduleria.Entity.Producto;

import com.LoDeNico.Verduleria.Entity.Detalle.detalleBoleta;
import com.LoDeNico.Verduleria.Entity.Detalle.detallePedido;
import com.LoDeNico.Verduleria.Entity.Pago;
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
@Table(name = "Boleta")
public class Boleta {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @Column(name = "paga")
    private boolean paga;

    @Column(name = "fRecibo")
    private Date fRecibo;

    @Column(name = "monto")
    private double monto;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    private List<detalleBoleta> detallesBoleta;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    private List<Pago> pagos;

}
