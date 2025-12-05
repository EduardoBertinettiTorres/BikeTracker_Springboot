package cstsi_tads_eduardo;

import cstsi_tads_eduardo.infra.security.TokenService;
import cstsi_tads_eduardo.Usuario.Usuario;
import cstsi_tads_eduardo.Usuario.UsuarioRepository; // Ou AutenticacaoService se você tiver
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpMethod.*;

@SpringBootTest(classes = TadsEduardoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseAPIIntegracaoTest {

    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    private UserDetailsService userDetailsService; // Usando o padrão do Spring Security

    @Autowired
    private TokenService tokenService;

    private String jwtToken = "";

    @BeforeEach
    public void setupTest() {
        // 1. Carrega o usuário que inserimos no data.sql
        // O professor usou "admin@email.com", vamos manter para facilitar
        Usuario user = (Usuario) userDetailsService.loadUserByUsername("admin@email.com");
        assertNotNull(user);

        // 2. Gera um token válido para ele
        jwtToken = tokenService.geraToken(user); // Verifique se o nome do método é geraToken ou gerarToken no seu service
        assertNotNull(jwtToken);
    }

    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return headers;
    }

    protected <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, POST, new HttpEntity<>(body, headers), responseType);
    }

    protected <T> ResponseEntity<T> put(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, PUT, new HttpEntity<>(body, headers), responseType);
    }

    protected <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, GET, new HttpEntity<>(headers), responseType);
    }

    protected <T> ResponseEntity<T> delete(String url, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, DELETE, new HttpEntity<>(headers), responseType);
    }
}