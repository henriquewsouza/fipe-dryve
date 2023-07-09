package com.dryve.fipedryve.entity;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "modelos")
@Getter
@Setter
@NoArgsConstructor
public class Modelo {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "fipe_id", nullable = false)
    private int fipeId;

    @OneToMany(mappedBy = "modelo")
    private List<Veiculo> veiculos;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;



    
}
