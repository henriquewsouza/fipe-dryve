package com.dryve.fipedryve.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "placa", nullable = false)
    private String placa;

    @Column(name = "preco_anuncio", nullable = false)
    private BigDecimal precoAnuncio;

    @Column(name = "ano", nullable = false)
    private int ano;

    @Column(name = "preco_fipe", nullable = false)
    private BigDecimal precoFipe;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    @Override
    public String toString() {
        return String.format("Veiculo [placa=%s, ano=%d, modelo=%s, precoFipe=%.2f]", placa, ano, modelo, precoFipe);
    }
}
