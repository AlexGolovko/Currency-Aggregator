package com.golovkobalak.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Bank {


    @Id
    @GeneratedValue
    private Long id;

    private String bankName;

    public Bank(String bankName) {
        this.bankName = bankName;
    }
}
