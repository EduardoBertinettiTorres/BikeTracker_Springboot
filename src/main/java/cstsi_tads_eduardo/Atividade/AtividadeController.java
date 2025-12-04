package cstsi_tads_eduardo.Atividade;

import cstsi_tads_eduardo.Usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/atividades")
public class AtividadeController {

    private final AtividadeRepository repository;

    public AtividadeController(AtividadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<AtividadeDtoResponse>> findAll() {
        // Retorna todas convertidas para DTO
        return ResponseEntity.ok(repository.findAll().stream().map(AtividadeDtoResponse::new).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<AtividadeDtoResponse> findById(@PathVariable Long id,
                                                         @AuthenticationPrincipal Usuario usuarioLogado) {
        var optionalAtividade = repository.findById(id);

        if (optionalAtividade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var atividade = optionalAtividade.get();

        // Verifica se é pública OU se pertence ao usuário logado
        if (!atividade.isPublica() && !atividade.getUsuario().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(new AtividadeDtoResponse(atividade));
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<List<AtividadeDtoResponse>> findByNome(@PathVariable String nome,
                                                                 @AuthenticationPrincipal Usuario usuarioLogado) {
        var atividades = repository.findByNome(nome + "%");

        if (atividades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Filtra para retornar apenas as que o usuário pode ver
        var permitidas = atividades.stream()
                .filter(a -> a.isPublica() || a.getUsuario().getId().equals(usuarioLogado.getId()))
                .map(AtividadeDtoResponse::new)
                .toList();

        return ResponseEntity.ok(permitidas);
    }

    @PostMapping
    public ResponseEntity<URI> insert(@Valid @RequestBody AtividadeDTOPost dados,
                                      @AuthenticationPrincipal Usuario usuarioLogado,
                                      UriComponentsBuilder uriBuilder) {

        // Criação usando Setters (mais seguro e claro que construtor gigante)
        var atividade = new Atividade();
        atividade.setNome(dados.nome());
        atividade.setDescricao(dados.descricao());
        atividade.setDistancia(dados.distancia());
        atividade.setTempo(dados.tempo());
        atividade.setData(dados.data());
        atividade.setTipoBicicleta(dados.tipoBicicleta());
        atividade.setPublica(dados.publica());

        // VINCULA O USUÁRIO LOGADO (Correção do erro 409)
        atividade.setUsuario(usuarioLogado);

        repository.save(atividade);

        var location = uriBuilder.path("api/v1/atividades/{id}").buildAndExpand(atividade.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<AtividadeDtoResponse> update(@PathVariable("id") Long id,
                                                       @Valid @RequestBody AtividadeDTOPut dados,
                                                       @AuthenticationPrincipal Usuario usuarioLogado) {
        var optionalAtividade = repository.findById(id);

        if (optionalAtividade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var atividade = optionalAtividade.get();

        // SEGURANÇA: Só o dono pode editar
        if (!atividade.getUsuario().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(403).build();
        }

        // Atualiza os dados
        atividade.setNome(dados.nome());
        atividade.setDescricao(dados.descricao());
        atividade.setDistancia(dados.distancia());
        atividade.setTempo(dados.tempo());
        atividade.setData(dados.data());
        atividade.setTipoBicicleta(dados.tipoBicicleta());
        atividade.setPublica(dados.publica());

        repository.save(atividade);

        return ResponseEntity.ok(new AtividadeDtoResponse(atividade));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id,
                                       @AuthenticationPrincipal Usuario usuarioLogado) {
        var optionalAtividade = repository.findById(id);

        if (optionalAtividade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var atividade = optionalAtividade.get();

        // SEGURANÇA: Só o dono pode deletar
        if (!atividade.getUsuario().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(403).build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}