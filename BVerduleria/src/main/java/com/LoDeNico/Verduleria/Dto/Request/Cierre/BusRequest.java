package com.LoDeNico.Verduleria.Dto.Request.Cierre;

import com.LoDeNico.Verduleria.Dto.Request.FechaRequest;
import com.LoDeNico.Verduleria.Dto.Request.MontoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusRequest {
    private FechaRequest fechaRequest;
    private boolean t;
    private int cod;
    private MontoRequest montoRequest;
}
