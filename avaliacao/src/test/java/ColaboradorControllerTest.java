import com.jihad.avaliacao.Models.Colaborador;
import com.jihad.avaliacao.Models.Perfil;
import com.jihad.avaliacao.controllers.ColaboradorController;
import com.jihad.avaliacao.services.ColaboradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ColaboradorControllerTest {

    @Mock
    private ColaboradorService colaboradorService;

    @InjectMocks
    private ColaboradorController colaboradorController;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testCadastrar() {
        Colaborador colaborador = new Colaborador("João Silva", "joao.silva@mail.com", Perfil.COLABORADOR);

        when(colaboradorService.cadastrarColaborador(any(Colaborador.class)))
                .thenReturn(colaborador);

        ResponseEntity<Colaborador> response = colaboradorController.cadastrar(colaborador);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(colaborador, response.getBody());

        System.out.println("testCadastrar:");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Colaborador: " + response.getBody().getNome() + ", " + response.getBody().getEmail() + ", " + response.getBody().getPerfil());
    }

    @Test
    public void testAlterarPerfil() {

        Long colaboradorId = 1L;
        doNothing().when(colaboradorService).alterarPerfil(colaboradorId, Perfil.ADMIN);

        Colaborador colaborador = new Colaborador("Maria Silva", "maria.silva@mail.com", Perfil.ADMIN);
        ResponseEntity<Void> response = colaboradorController.alterarPerfil(colaboradorId, colaborador);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(colaboradorService, times(1)).alterarPerfil(colaboradorId, Perfil.ADMIN);

        System.out.println("testAlterarPerfil:");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("ID do colaborador alterado: " + colaboradorId);
        System.out.println("Novo perfil: " + Perfil.ADMIN);
    }

    @Test
    public void testListarColaboradores() {

        List<Colaborador> colaboradores = Arrays.asList(
                new Colaborador("João Silva", "joao.silva@mail.com", Perfil.COLABORADOR),
                new Colaborador("Maria Silva", "maria.silva@mail.com", Perfil.ADMIN)
        );

        when(colaboradorService.listarTodos()).thenReturn(colaboradores);

        ResponseEntity<List<Colaborador>> response = colaboradorController.listarColaboradores();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(colaboradores, response.getBody());

        System.out.println("testListarColaboradores:");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Colaboradores:");
        for (Colaborador colab : response.getBody()) {
            System.out.println("Nome: " + colab.getNome() + ", Email: " + colab.getEmail() + ", Perfil: " + colab.getPerfil());
        }
    }
}
