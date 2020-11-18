package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto implements Serializable {

    private int id;

    @JsonProperty("ten")
    private String name;

    @JsonProperty("cmt")
    private String cmt;

    @JsonProperty("maSoBangLai")
    private String licenseCode;

    @JsonProperty("loaiBangLai")
    private String licenseType;

    @JsonProperty("diaChi")
    private String address;

    @JsonProperty("ngaySinh")
    private Date dateOfBirth;

    @JsonProperty("thamNien")
    private int seniority;
}
