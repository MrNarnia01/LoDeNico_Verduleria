package com.LoDeNico.Verduleria.Dto.Request.Cierre;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusRequest {
    private Timestamp f1;
    private Timestamp f2;
    private boolean t;
    private int cod;
    private String s1;
    private Double m1;
    private Double m2;
}
