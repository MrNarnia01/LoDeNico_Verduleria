package com.LoDeNico.Verduleria.Dto.Response.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoteResponse {
    private Long id;
    private String nombre;
    private int nLote;
    private Timestamp fVenci;

}
