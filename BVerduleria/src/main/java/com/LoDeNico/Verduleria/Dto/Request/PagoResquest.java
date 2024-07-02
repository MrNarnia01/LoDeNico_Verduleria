package com.LoDeNico.Verduleria.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagoResquest {
    private Long idB;
    private String tipo;
    private Date fPago;
    private double monto;
}
