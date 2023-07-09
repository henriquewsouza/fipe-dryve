package com.dryve.fipedryve.entity;


import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "marcas")
@Getter
@Setter
@NoArgsConstructor
public class Marca {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "fipe_id", nullable = false)
    private int fipeId;
    
}
