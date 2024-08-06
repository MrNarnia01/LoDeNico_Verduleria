package com.LoDeNico.Verduleria.Dto.Response.Cuenta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private int codArea;
    private int tel;
    private String calle;
    private int altura;
    private List<CuentaCorrienteResponse> cuentaCorrienteResponseList;
}
