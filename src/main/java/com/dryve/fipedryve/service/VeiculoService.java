package com.dryve.fipedryve.service;

import com.dryve.fipedryve.repository.ModeloRepository;
import com.dryve.fipedryve.repository.VeiculoRepository;
import com.dryve.fipedryve.repository.MarcaRepository;
import com.dryve.fipedryve.dto.MarcaDTO;
import com.dryve.fipedryve.entity.FipeRequestBody;
import com.dryve.fipedryve.entity.FipeResponse;
import com.dryve.fipedryve.entity.Marca;
import com.dryve.fipedryve.entity.Modelo;
import com.dryve.fipedryve.entity.Veiculo;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private FipeService fipeService;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    public VeiculoService(FipeService fipeService) {
        this.fipeService = fipeService;
    }

    public Veiculo saveVeiculo(Veiculo veiculo) {
        System.out.println(veiculo.toString());
        if (veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Veículo já cadastrado com esta placa");
        }
        Modelo modelo = veiculo.getModelo();
        System.out.println(modelo.toString());

        Marca marca = modelo.getMarca();
        System.out.println(marca.toString());
        FipeRequestBody fipeRequestBody = new FipeRequestBody();
        fipeRequestBody.setCodigoTabelaReferencia(fipeService.getTabelaReferencia());
        fipeRequestBody.setCodigoMarca(marca.getFipeId());
        fipeRequestBody.setCodigoModelo(modelo.getFipeId());
        fipeRequestBody.setCodigoTipoCombustivel(1);
        fipeRequestBody.setAno(String.format("%d-1", veiculo.getAno()));
        fipeRequestBody.setAnoModelo(veiculo.getAno());
        fipeRequestBody.setTipoConsulta("tradicional");

        FipeResponse fipeResponse = fipeService.consultaFipe(fipeRequestBody);
        System.out.println(fipeResponse.getValor());


        veiculo.setPrecoFipe(fipeResponse.getValorAsBigDecimal());
        veiculo.setDataCadastro(LocalDate.now());

        return veiculoRepository.save(veiculo);
    }

    public Veiculo getVeiculoByPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));
    }

    public List<MarcaDTO> getMarcasComTotalModelos() {
    List<Marca> marcas = marcaRepository.findAll();
    List<MarcaDTO> marcaDTOs = new ArrayList<>();

    for (Marca marca : marcas) {
        MarcaDTO marcaDTO = new MarcaDTO();
        marcaDTO.setId(marca.getId().toString());
        marcaDTO.setName(marca.getName());
        marcaDTO.setTotal_modelos(marcas.size());
        marcaDTOs.add(marcaDTO);
    }
    

    return marcaDTOs;
    }

    public MarcaDTO getMarcaById(String id) {
    UUID marcaId;
    List<Marca> marcas = marcaRepository.findAll();
    try {
        marcaId = UUID.fromString(id);
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("ID da marca inválido");
    }

    Marca marca = marcaRepository.findById(marcaId)
            .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada"));

    MarcaDTO marcaDTO = new MarcaDTO();
    marcaDTO.setId(marca.getId().toString());
    marcaDTO.setName(marca.getName());
    marcaDTO.setTotal_modelos(marcas.size());

    return marcaDTO;
    }


}

