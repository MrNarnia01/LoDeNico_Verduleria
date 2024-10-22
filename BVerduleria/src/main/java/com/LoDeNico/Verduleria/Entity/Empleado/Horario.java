package com.LoDeNico.Verduleria.Entity.Empleado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalTime;

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

    @Column(name = "entrada")
    private LocalTime entrada;

    @Column(name = "salida")
    private LocalTime salida;

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;

}
