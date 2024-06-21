package com.LoDeNico.Verduleria.Entity.Proveedor;

import com.LoDeNico.Verduleria.Entity.Empleado.Persona;
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
@Table(name = "Trabajador")
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

    @Column(name = "puesto")
    private String puesto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPersona")
    private Persona persona;

}
