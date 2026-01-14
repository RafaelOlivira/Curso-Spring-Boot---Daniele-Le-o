package com.example.demo.perfomance;

import com.example.demo.models.DespesaModel;
import com.example.demo.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*@Component*/
public class GestaoDeDespesaSeeder implements CommandLineRunner {

    @Autowired
    DespesaRepository despesaRepository;

    @Override
    public void run(String... args) throws Exception {
        List<DespesaModel> despesaModels = new ArrayList<>();

        for(int i=0;i<10;i++){
            DespesaModel despesaModel = new DespesaModel();
            despesaModel.setCategoria("Lazer");
            despesaModel.setData(LocalDate.now().minusDays(i % 30));
            despesaModel.setDescricao("Video Game PS"+i+1);
            despesaModel.setEmail("joao@gmail.com");
            despesaModel.setValor(BigDecimal.valueOf(10 + (i % 50)));

            despesaModels.add(despesaModel);
        }
        despesaRepository.saveAll(despesaModels);
    }
}
