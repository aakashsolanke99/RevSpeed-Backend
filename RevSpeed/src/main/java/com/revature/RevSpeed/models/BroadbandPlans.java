package com.revature.RevSpeed.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class BroadbandPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String planName;
    private String planType;

    private double price;
    private String speed;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;


    @OneToMany(mappedBy = "broadbandPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OTT> ott;

    public BroadbandPlans(String planName, String planType, double price, String speed,Service service, List<OTT> ott) {
        this.planName = planName;
        this.planType = planType;
        this.price = price;
        this.speed = speed;
        this.service=service;
        this.ott = ott;
        if (ott != null) {
            ott.forEach(ottItem -> ottItem.setBroadbandPlan(this));
        }
    }

}
