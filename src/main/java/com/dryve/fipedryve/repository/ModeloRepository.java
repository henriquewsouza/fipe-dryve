package com.dryve.fipedryve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import com.dryve.fipedryve.entity.Modelo;


public interface ModeloRepository extends JpaRepository<Modelo, UUID> {

    Optional<Modelo> findById(UUID id);

}
