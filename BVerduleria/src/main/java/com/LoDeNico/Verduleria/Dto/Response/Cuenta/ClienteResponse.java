package com.LoDeNico.Verduleria.Dto.Response.Cuenta;

import java.util.List;

public class ClienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private int codArea;
    private int tel;
    private String calle;
    private int altura;
    private List<CuenteCorrienteResponse> cuenteCorrienteResponseList;
}
