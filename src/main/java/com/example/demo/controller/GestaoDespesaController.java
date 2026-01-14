package com.example.demo.controller;

import com.example.demo.custom_messages.ErrorMessages;
import com.example.demo.models.DespesaModel;
import com.example.demo.usecase.BuscarDespesaUseCase;
import com.example.demo.usecase.CadastroDespesaUseCase;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/gestao")
@NoArgsConstructor
public class GestaoDespesaController {

    @Autowired
    CadastroDespesaUseCase cadastroDespesaUseCase;

    @Autowired
    BuscarDespesaUseCase buscarDespesaUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DespesaModel despesaModel){
        try {
            var result = cadastroDespesaUseCase.execute(despesaModel);
            return ResponseEntity.ok(result);
        }catch (IllegalArgumentException e) {
            var errorMessage = new ErrorMessages(e.getMessage(),"INVALID_PARAMS");
            return ResponseEntity.status(400).body(errorMessage);
        }
    }

    @GetMapping("/{email}")
    public List<DespesaModel> findByEmailAndDate(@PathVariable String email, @RequestParam(required = false)LocalDate data){
        return buscarDespesaUseCase.buscarEmailData(email,data);
    }
}
