package com.LoDeNico.Verduleria.Dto.Response.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {
    private Long id;
    private String nombre;
    private double precio;
    private int stock;
    private boolean unit;
    private List<LoteResponse> loteResponseList;
}
