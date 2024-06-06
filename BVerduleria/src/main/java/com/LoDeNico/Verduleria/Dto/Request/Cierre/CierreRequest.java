package com.LoDeNico.Verduleria.Dto.Request.Cierre;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CierreRequest {
    private boolean tipo;
    private double monto;
    private Date fRegis;
}
