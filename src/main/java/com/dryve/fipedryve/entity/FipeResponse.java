package com.dryve.fipedryve.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

@Getter
@Setter
public class FipeResponse {
    @JsonProperty("Valor")
    private String valor;

    @JsonProperty("Marca")
    private String marca;

    @JsonProperty("Modelo")
    private String modelo;

    @JsonProperty("AnoModelo")
    private int anoModelo;

    @JsonProperty("Combustivel")
    private String combustivel;

    @JsonProperty("CodigoFipe")
    private String codigoFipe;

    @JsonProperty("MesReferencia")
    private String mesReferencia;

    @JsonProperty("Autenticacao")
    private String autenticacao;

    @JsonProperty("TipoVeiculo")
    private int tipoVeiculo;

    @JsonProperty("SiglaCombustivel")
    private String siglaCombustivel;

    @JsonProperty("DataConsulta")
    private String dataConsulta;

    public BigDecimal getValorAsBigDecimal() {
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("pt", "BR"));
        df.setParseBigDecimal(true);
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        try {
            return (BigDecimal) df.parse(valor.replaceAll("[^\\d.,]", ""));
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse 'valor' to BigDecimal", e);
        }
    }

}
