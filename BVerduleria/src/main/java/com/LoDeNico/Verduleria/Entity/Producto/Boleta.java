package com.LoDeNico.Verduleria.Entity.Producto;

import com.LoDeNico.Verduleria.Entity.Detalle.detalleBoleta;
import com.LoDeNico.Verduleria.Entity.Pago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Boleta")
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nB")
    private Long nB;

    @OneToOne
    @JoinColumn(name = "id_Pedido")
    private Pedido pedido;

    @Column(name = "paga")
    private boolean paga;

    @CreationTimestamp
    @Column(name = "fRecibo")
    private Timestamp fRecibo;

    @Column(name = "monto")
    private double monto;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    private List<detalleBoleta> detallesBoleta;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    public double allPagado(){
        double total = 0;
        if (!this.pagos.isEmpty()){
            for (Pago pa: this.pagos){
                total+=pa.getMonto();
            }
        }
        return this.getMonto()-total;
    }
    
    

}
