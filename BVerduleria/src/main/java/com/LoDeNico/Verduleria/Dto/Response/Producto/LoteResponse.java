package com.LoDeNico.Verduleria.Dto.Response.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoteResponse {
    private Long id;
    private String nombre;
    private Timestamp fVenci;
    private boolean term;

}
