package com.jihad.avaliacao.services;

import com.jihad.avaliacao.Models.Evento;
import com.jihad.avaliacao.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    // Método para criar um novo evento
    public Evento criarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public EventoRepository getEventoRepository() {
        return eventoRepository;
    }

    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    // Método para obter um evento por ID
    public Optional<Evento> obterEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    // Método para listar todos os eventos
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    // Método para deletar um evento
    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    // Método para atualizar um evento existente
    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        Optional<Evento> eventoExistente = eventoRepository.findById(id);
        if (eventoExistente.isPresent()) {
            Evento evento = eventoExistente.get();
            evento.setNome(eventoAtualizado.getNome());
            evento.setDescricao(eventoAtualizado.getDescricao());
            evento.setDataInicio(eventoAtualizado.getDataInicio());
            evento.setDataFim(eventoAtualizado.getDataFim());
            // Atualize outras propriedades conforme necessário
            return eventoRepository.save(evento);
        } else {
            throw new IllegalArgumentException("Evento não encontrado");
        }
    }
}