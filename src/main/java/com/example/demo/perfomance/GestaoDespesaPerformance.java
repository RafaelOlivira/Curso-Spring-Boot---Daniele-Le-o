package com.example.demo.perfomance;

import com.example.demo.models.DespesaModel;
import com.example.demo.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/gestao/performance")
@RestController
public class GestaoDespesaPerformance {

    @Autowired
    DespesaRepository despesaRepository;

    @GetMapping("/sem-paginacao")
    public ResponseEntity<List<DespesaModel>> listarSemPaginacao() {
        long inicio = System.currentTimeMillis();
        var result = despesaRepository.findAll();

        long fim = System.currentTimeMillis();
        System.out.println("Tempo (sem paginação) " + (fim - inicio) + " ms");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/com-paginacao")
    public ResponseEntity<Page<DespesaModel>> listarComPaginacao(Pageable pageable) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        var result = despesaRepository.findAll(pageable);
        stopWatch.stop();
        System.out.println("Tempo (com paginação) " + (stopWatch.getTotalTimeMillis()) + " ms");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/com-paginacao/{email}")
    public ResponseEntity<Page<DespesaModel>> listarComPaginacaoEmail(@PathVariable String email, Pageable pageable) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var result = despesaRepository.findByEmail(email, pageable);
        stopWatch.stop();
        System.out.println("Tempo (com paginação) " + (stopWatch.getTotalTimeMillis()) + " ms");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/com-paginacao/cat/{categoria}")
    public ResponseEntity<Page<DespesaModel>> listarComPaginacaoCategoria(@PathVariable String categoria, Pageable pageable) {
        var result = despesaRepository.findByCategoria(categoria, pageable);
        return ResponseEntity.ok(result);
    }

    @Cacheable(value = "gastosPorEmailCache", key = "#email + '-'+ #pageable.pageNumber + '-' + #pageable.pageSize + '-'")
    @GetMapping("/cache/{email}")
    public ResponseEntity<Page<DespesaModel>> buscarPorEmail(@PathVariable String email, Pageable pageable) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var result = despesaRepository.findByEmail(email, pageable);
        stopWatch.stop();

        System.out.println("Tempo (com paginação) " + (stopWatch.getTotalTimeMillis()) + " ms");
        return ResponseEntity.ok(result);
    }
}
