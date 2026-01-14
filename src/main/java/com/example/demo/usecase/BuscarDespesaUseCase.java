package com.example.demo.usecase;

import com.example.demo.models.DespesaModel;
import com.example.demo.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Service
public class BuscarDespesaUseCase {

    @Autowired
    private DespesaRepository despesaRepository;


    public List<DespesaModel> buscarEmailData(String email, LocalDate data) {
        List<DespesaModel> despesas;
        if(data != null){
            despesas = despesaRepository.findByEmailAndData(email,data);
        }else{
            despesas = despesaRepository.findByEmail(email);
        }
        return despesas;
    }
}
