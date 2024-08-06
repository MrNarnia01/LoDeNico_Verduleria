package com.LoDeNico.Verduleria.Entity.Empleado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "Horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hId;

    @CreationTimestamp
    @Column(name = "entrada")
    private Timestamp entrada;

    @UpdateTimestamp
    @Column(name = "salida")
    private Timestamp salida;

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;

}
