package com.LoDeNico.Verduleria.Entity.Cuenta;
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
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "codArea")
    private int codArea;

    @Column(name = "tel")
    private int tel;

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private int altura;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<CuentaCorriente> CuentasCorriente;

}
