package com.LoDeNico.Verduleria.Dto.Request;

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
public class BusRequest {
    private Date f1;
    private Date f2;
    private boolean b;
    private int i;
    private String s1;
    private Double m1;
    private Double m2;
}
