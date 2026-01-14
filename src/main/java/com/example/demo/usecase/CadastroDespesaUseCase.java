package com.example.demo.usecase;


import com.example.demo.models.DespesaModel;
import com.example.demo.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CadastroDespesaUseCase {

    @Autowired
    private DespesaRepository despesaRepository;

    public DespesaModel execute(DespesaModel despesaModel) {
        if (despesaModel.getCategoria() == null ||
                despesaModel.getData() == null ||
                despesaModel.getDescricao() == null ||
                despesaModel.getEmail( ) == null ||
                despesaModel.getValor() == null) {
            throw new IllegalArgumentException("Preencha todos os campos!");
        }
            return despesaRepository.save(despesaModel);
    }
}
