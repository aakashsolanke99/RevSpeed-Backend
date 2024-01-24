package com.revature.RevSpeed.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class BusinessPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String planName;
    private String planType;

    private double price;
    private String speed;


    public BusinessPlans(String planName, String planType, double price, String speed) {
        this.planName = planName;
        this.planType = planType;
        this.price = price;
        this.speed = speed;
    }
}
