package com.dryve.fipedryve.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoResponseDTO {
    private String placa;
    private BigDecimal preco_anuncio;
    private int ano;
    private BigDecimal preco_fipe;
    
    private LocalDate data_cadastro;
    private String modelo;
    private String marca;
}
