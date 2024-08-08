package com.LoDeNico.Verduleria.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FechaRequest {
    private Timestamp f1;
    private Timestamp f2;
}
