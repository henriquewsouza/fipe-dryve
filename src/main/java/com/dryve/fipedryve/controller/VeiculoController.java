package com.dryve.fipedryve.controller;

import com.dryve.fipedryve.entity.Veiculo;
import com.dryve.fipedryve.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dryve.fipedryve.dto.VeiculoResponseDTO;


@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo savedVeiculo = veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.ok(savedVeiculo);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<VeiculoResponseDTO> getVeiculoByPlaca(@PathVariable String placa) {
        Veiculo veiculo = veiculoService.getVeiculoByPlaca(placa);
        
        VeiculoResponseDTO veiculoResponseDTO = new VeiculoResponseDTO(
            veiculo.getPlaca(),
            veiculo.getPrecoAnuncio(),
            veiculo.getAno(),
            veiculo.getPrecoFipe(),
            veiculo.getDataCadastro(),
            veiculo.getModelo().getName(),
            veiculo.getModelo().getMarca().getName()
        );
        
        return ResponseEntity.ok(veiculoResponseDTO);
    }

}

