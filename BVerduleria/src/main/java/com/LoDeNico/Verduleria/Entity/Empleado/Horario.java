package com.LoDeNico.Verduleria.Entity.Empleado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "entrada")
    private Date entrada;

    @Column(name = "salida")
    private Date salida;

    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado empleado;

}
