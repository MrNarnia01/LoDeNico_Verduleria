package com.LoDeNico.Verduleria.Entity.Empleado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPersona")
    private Persona persona;

    @Column(name = "mail")
    private String mail;

    @Column(name = "contra")
    private String contra;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Horario> horarios;

}
