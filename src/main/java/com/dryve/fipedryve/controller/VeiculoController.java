package com.dryve.fipedryve.controller;

import com.dryve.fipedryve.entity.Veiculo;
import com.dryve.fipedryve.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}

