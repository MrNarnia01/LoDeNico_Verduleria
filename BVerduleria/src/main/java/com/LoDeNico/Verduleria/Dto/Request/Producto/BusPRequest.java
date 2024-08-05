package com.LoDeNico.Verduleria.Dto.Request.Producto;

import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BusPRequest {
    private String nombre;
    private int uni;
    private MontoRequest montoRequest;
}
