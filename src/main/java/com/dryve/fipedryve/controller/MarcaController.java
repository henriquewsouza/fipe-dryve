package com.dryve.fipedryve.controller;

import com.dryve.fipedryve.dto.MarcaDTO;
import com.dryve.fipedryve.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final VeiculoService veiculoService;

    @Autowired
    public MarcaController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("")
    public ResponseEntity<List<MarcaDTO>> getMarcasComTotalModelos() {
        List<MarcaDTO> marcas = veiculoService.getMarcasComTotalModelos();
        return ResponseEntity.ok(marcas);
    }

}