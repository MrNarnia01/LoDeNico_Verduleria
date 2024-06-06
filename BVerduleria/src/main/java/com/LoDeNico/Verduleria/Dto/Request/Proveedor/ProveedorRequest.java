package com.LoDeNico.Verduleria.Dto.Request.Proveedor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorRequest {
    private String negocio;
    private String calle;
    private int altura;
}
