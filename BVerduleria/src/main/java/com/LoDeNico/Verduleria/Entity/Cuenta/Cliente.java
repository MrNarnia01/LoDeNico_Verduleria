package com.LoDeNico.Verduleria.Entity.Cuenta;
import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPersona")
    private Persona persona;

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private int altura;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<CuentaCorriente> CuentasCorriente;

}
