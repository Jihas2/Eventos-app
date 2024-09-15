package com.jihad.avaliacao.services;



import com.jihad.avaliacao.Models.Colaborador;
import com.jihad.avaliacao.Models.Perfil;
import com.jihad.avaliacao.repositories.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador cadastrarColaborador(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public void alterarPerfil(Long id, Perfil perfil) {
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById(id);
        if (colaboradorOptional.isPresent()) {
            Colaborador colaborador = colaboradorOptional.get();
            colaborador.setPerfil(perfil);
            colaboradorRepository.save(colaborador);
        } else {
            throw new IllegalArgumentException("Colaborador não encontrado");
        }
    }

    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll();
    }
}
