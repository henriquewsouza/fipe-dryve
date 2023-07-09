package com.dryve.fipedryve.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FipeRequestBody {

    @JsonProperty("codigoTabelaReferencia")
    private int codigoTabelaReferencia;

    @JsonProperty("codigoTipoVeiculo")
    private final int codigoTipoVeiculo = 1;

    @JsonProperty("codigoMarca")
    private int codigoMarca;

    @JsonProperty("ano")
    private String ano;

    @JsonProperty("codigoTipoCombustivel")
    private int codigoTipoCombustivel;

    @JsonProperty("anoModelo")
    private int anoModelo;

    @JsonProperty("codigoModelo")
    private int codigoModelo;

    @JsonProperty("tipoConsulta")
    private String tipoConsulta;

    @Override
    public String toString() {
        return "FipeRequestBody{" +
                "codigoTabelaReferencia=" + codigoTabelaReferencia +
                ", codigoTipoVeiculo=" + codigoTipoVeiculo +
                ", codigoMarca=" + codigoMarca +
                ", ano='" + ano + '\'' +
                ", codigoTipoCombustivel=" + codigoTipoCombustivel +
                ", anoModelo=" + anoModelo +
                ", codigoModelo=" + codigoModelo +
                ", tipoConsulta='" + tipoConsulta + '\'' +
                '}';
    }

}

