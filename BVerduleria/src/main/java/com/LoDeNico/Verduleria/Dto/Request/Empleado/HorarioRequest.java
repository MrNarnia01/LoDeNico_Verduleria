package com.LoDeNico.Verduleria.Dto.Request.Empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioRequest {
    private Long idE;
    private Date entrada;
}
