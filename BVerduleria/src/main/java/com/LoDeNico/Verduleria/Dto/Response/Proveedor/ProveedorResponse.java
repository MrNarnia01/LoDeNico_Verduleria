package com.LoDeNico.Verduleria.Dto.Response.Proveedor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorResponse {
    private Long id;
    private String negocio;
    private String calle;
    private int altura;
    private List<TrabajadorResponse> trabajadorResponseList;
}
