package com.bg.playground.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "expense")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Expense implements Serializable {


    private static final long serialVersionUID = 2325098035315880104L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "price")
    private float price;

}
