package com.golovkobalak.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

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
