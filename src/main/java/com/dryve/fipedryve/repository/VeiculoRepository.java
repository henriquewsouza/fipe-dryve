package com.dryve.fipedryve.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dryve.fipedryve.entity.Veiculo;


public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
        boolean existsByPlaca(String placa);
        Optional<Veiculo> findByPlaca(String placa);
}
