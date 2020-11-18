package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
    private int id;

    @JsonProperty("diemDau")
    private String start;

    @JsonProperty("diemCuoi")
    private String end;

    @JsonProperty("doDai")
    private int length;

    @JsonProperty("doPhucTap")
    private int description;
}

