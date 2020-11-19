package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto implements Serializable {

    private int id;

    @JsonProperty("bienXe")
    private String licensePlate;

    @JsonProperty("mauXe")
    private String color;

    @JsonProperty("hangSanXuat")
    private String manufacturer;

    @JsonProperty("doiXe")
    private String version;

    @JsonProperty("model")
    private int model;

    @JsonProperty("soGhe")
    private int seats;

    @JsonProperty("soNamSuDung")
    private int yearsOfUse;

    @JsonProperty("ngayBaoDuongCuoiCung")
    private Date lastMaintenanceDate;
}
