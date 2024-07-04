package com.LoDeNico.Verduleria.Dto.Request.Producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoteRequest {
    private Long idP;
    private Timestamp fVenci;
}
