package com.example.demo.repositories;

import com.example.demo.models.DespesaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaModel, UUID> {

    List<DespesaModel> findByEmail(String email);
    List<DespesaModel> findByEmailAndData(String email, LocalDate data);

    Page<DespesaModel> findByEmail(String email, Pageable pageable);

    Page<DespesaModel> findByCategoria(String categoria,Pageable pageable);
}
