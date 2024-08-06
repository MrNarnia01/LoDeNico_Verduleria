package com.LoDeNico.Verduleria.Dto.Response.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private int codArea;
    private int tel;
    private String mail;
    private String contra;
    private List<HorarioResponse> horarioResponseList;
}
