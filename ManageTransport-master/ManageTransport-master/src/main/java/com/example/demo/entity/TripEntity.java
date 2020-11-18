package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip")
@Builder
public class TripEntity implements Serializable {
    private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false )
        private int id;

        @Column(name = "code")
        private int code;

        @Column(name = "route")
        private String route;

        @Column(name = "driver_name")
        private String driverName;

        @Column(name = "assistant_driver_name")
        private String assistantDriverName;

        @Column(name = "guest_number")
        private int guestNumber;

        @Column(name = "price")
        private int price;
}
