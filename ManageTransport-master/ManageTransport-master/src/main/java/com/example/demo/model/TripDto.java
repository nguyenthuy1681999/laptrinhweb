package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto implements Serializable {
    private int id;

    @JsonProperty("maSo")
    private int code;

    @JsonProperty("tuyen")
    private String route;

    @JsonProperty("laiXe")
    private String driverName;

    @JsonProperty("phuXe")
    private String assistantDriverName;

    @JsonProperty("soKhach")
    private int guestNumber;

    @JsonProperty("giaVe")
    private int price;
}

