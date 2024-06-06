package com.LoDeNico.Verduleria.Dto.Response.Cierre;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CierreResponse {
    private Long id;
    private boolean tipo;
    private double monto;
    private Date fRegis;

}
