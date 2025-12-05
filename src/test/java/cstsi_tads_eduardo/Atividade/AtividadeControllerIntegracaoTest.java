package cstsi_tads_eduardo.Atividade;

import cstsi_tads_eduardo.BaseAPIIntegracaoTest;
import cstsi_tads_eduardo.TadsEduardoApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TadsEduardoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // <--- 1. Habilita a ordenação
class AtividadeControllerIntegracaoTest extends BaseAPIIntegracaoTest {

    private ResponseEntity<List<AtividadeDtoResponse>> getAtividadesList(String url) {
        var headers = getHeaders();
        return rest.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {}
        );
    }

    @Test
    @Order(1) // <--- Roda PRIMEIRO (O banco está limpo, só com o data.sql)
    @DisplayName("Deve retornar lista de atividades (findAll)")
    void findAll() {
        var response = getAtividadesList("/api/v1/atividades");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().size()); // Espera 3 (do data.sql)
    }

    @Test
    @Order(2) // <--- Roda SEGUNDO
    @DisplayName("Deve retornar uma atividade por ID (findById)")
    void findById() {
        var response = get("/api/v1/atividades/1", AtividadeDtoResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Como o update ainda não rodou, o nome ainda é o original
        assertEquals("Pedalada Matinal", response.getBody().nome());
    }

    @Test
    @Order(3) // <--- Roda TERCEIRO
    @DisplayName("Deve buscar atividades por nome (findByNome)")
    void findByNome() {
        var response = getAtividadesList("/api/v1/atividades/nome/Pedalada");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Pedalada Matinal", response.getBody().get(0).nome());
    }

    @Test
    @Order(4) // <--- Roda QUARTO (Agora vamos sujar o banco)
    @DisplayName("Deve inserir uma nova atividade (insert)")
    void insert() {
        var novaAtividade = new AtividadeDTOPost(
                "Teste Insert",
                "Descrição Teste",
                10.0,
                20.0,
                new Date(),
                "Speed",
                true
        );

        var response = post("/api/v1/atividades", novaAtividade, Void.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().getLocation());
    }

    @Test
    @Order(5) // <--- Roda QUINTO (Altera o ID 1)
    @DisplayName("Deve atualizar uma atividade existente (update)")
    void update() {
        var dadosAtualizados = new AtividadeDTOPut(
                "Pedalada Atualizada",
                "Volta no parque",
                10.5,
                30.0,
                new Date(),
                "MTB",
                true
        );

        var response = put("/api/v1/atividades/1", dadosAtualizados, AtividadeDtoResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Pedalada Atualizada", response.getBody().nome());
    }

    @Test
    @Order(6) // <--- Roda ÚLTIMO (Remove dados)
    @DisplayName("Deve deletar uma atividade (delete)")
    void deleteTest() {
        // Deletando o ID 2 para não interferir no ID 1 que usamos acima
        var response = delete("/api/v1/atividades/2", Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        var busca = get("/api/v1/atividades/2", Object.class);
        assertEquals(HttpStatus.NOT_FOUND, busca.getStatusCode());
    }
}