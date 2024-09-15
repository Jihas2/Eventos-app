import com.jihad.avaliacao.Models.Evento;
import com.jihad.avaliacao.controllers.EventoController;
import com.jihad.avaliacao.services.EventoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventoControllerTest {

    @Mock
    private EventoService eventoService;

    @InjectMocks
    private EventoController eventoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarEvento() {
        Evento evento = new Evento("Evento 1", "Descrição do Evento", LocalDate.now(), LocalDate.now().plusDays(1));

        when(eventoService.criarEvento(any(Evento.class))).thenReturn(evento);

        ResponseEntity<Evento> response = eventoController.criarEvento(evento);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(evento, response.getBody());

        System.out.println("testCriarEvento:");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Evento Criado: " + response.getBody().getNome() + ", " + response.getBody().getDescricao() + ", Início: " + response.getBody().getDataInicio() + ", Fim: " + response.getBody().getDataFim());
    }

    @Test
    public void testObterEventoPorId() {

        Evento evento = new Evento("Evento 1", "Descrição do Evento", LocalDate.now(), LocalDate.now().plusDays(1));
        when(eventoService.obterEventoPorId(1L)).thenReturn(Optional.of(evento));

        ResponseEntity<Evento> response = eventoController.obterEventoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(evento, response.getBody());

        System.out.println("testObterEventoPorId:");
        System.out.println("Status: " + response.getStatusCode());
        if (response.getBody() != null) {
            System.out.println("Evento Obtido: " + response.getBody().getNome() + ", " + response.getBody().getDescricao() + ", Início: " + response.getBody().getDataInicio() + ", Fim: " + response.getBody().getDataFim());
        } else {
            System.out.println("Nenhum evento encontrado.");
        }
    }

    @Test
    public void testListarEventos() {

        List<Evento> eventos = Arrays.asList(
                new Evento("Evento 1", "Descrição do Evento", LocalDate.now(), LocalDate.now().plusDays(1)),
                new Evento("Evento 2", "Descrição do Evento 2", LocalDate.now().plusDays(2), LocalDate.now().plusDays(3))
        );
        when(eventoService.listarEventos()).thenReturn(eventos);

        ResponseEntity<List<Evento>> response = eventoController.listarEventos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(eventos, response.getBody());

        System.out.println("testListarEventos:");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Eventos Listados:");
        for (Evento evento : response.getBody()) {
            System.out.println("Nome: " + evento.getNome() + ", Descrição: " + evento.getDescricao() + ", Início: " + evento.getDataInicio() + ", Fim: " + evento.getDataFim());
        }
    }

    @Test
    public void testDeletarEvento() {

        doNothing().when(eventoService).deletarEvento(1L);

        ResponseEntity<Void> response = eventoController.deletarEvento(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventoService, times(1)).deletarEvento(1L);

        System.out.println("testDeletarEvento:");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Evento deletado com ID: 1L");
    }
}
