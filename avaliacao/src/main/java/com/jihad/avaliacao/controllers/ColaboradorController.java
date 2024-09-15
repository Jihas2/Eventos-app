package com.jihad.avaliacao.controllers;

import com.jihad.avaliacao.Models.Colaborador;
import com.jihad.avaliacao.Models.Perfil;
import com.jihad.avaliacao.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Colaborador> cadastrar(@RequestBody Colaborador colaborador) {
        Colaborador novoColaborador = colaboradorService.cadastrarColaborador(colaborador);
        return ResponseEntity.ok(novoColaborador);
    }

    @PutMapping("/alterar-perfil/{id}")
    public ResponseEntity<Void> alterarPerfil(@PathVariable Long id, @RequestBody Colaborador colaborador) {
        colaboradorService.alterarPerfil(id, colaborador.getPerfil());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> listarColaboradores() {
        return ResponseEntity.ok(colaboradorService.listarTodos());
    }
}

