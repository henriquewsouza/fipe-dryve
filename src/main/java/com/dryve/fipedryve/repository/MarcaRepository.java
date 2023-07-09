package com.dryve.fipedryve.repository;

import com.dryve.fipedryve.entity.Marca;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MarcaRepository extends JpaRepository<Marca, UUID> {
    public List<Marca> findAll();
}
