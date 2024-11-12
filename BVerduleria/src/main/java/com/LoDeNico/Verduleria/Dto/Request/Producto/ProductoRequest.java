package com.LoDeNico.Verduleria.Dto.Request.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest {
    private String nombre;
    private double precio;
    private boolean unit;
}
