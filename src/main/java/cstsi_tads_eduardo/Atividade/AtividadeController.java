package cstsi_tads_eduardo.Atividade;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.access.annotation.Secured;

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
    public ResponseEntity<List<AtividadeDtoResponse>> findAll(){
        return ResponseEntity.ok(repository.findAll().stream().map(AtividadeDtoResponse::new).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Atividade> findById(@PathVariable Long id){
        var optionalAtividade = repository.findById(id);
        if(optionalAtividade.isPresent()){
            return ResponseEntity.ok(optionalAtividade.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<List<Atividade>> findByNome(@PathVariable  String nome){
        var atividades = repository.findByNome(nome + "%");
        if(atividades.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atividades);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<URI> insert(@RequestBody AtividadeDTOPost atividadeDTOPost, UriComponentsBuilder uriBuilder){
        var p = repository.save(new Atividade(
                atividadeDTOPost.nome(),
                atividadeDTOPost.descricao(),
                atividadeDTOPost.distancia(),
                atividadeDTOPost.tempo(),
                atividadeDTOPost.data(),
                atividadeDTOPost.tipoBicicleta(),
                atividadeDTOPost.publica()
        ));
        var location = uriBuilder.path("api/v1/atividades/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<AtividadeDtoResponse> update(@PathVariable("id") Long id, @Valid @RequestBody AtividadeDTOPut atividadeDTOPut){
        var p = repository.save(new Atividade(
                atividadeDTOPut.nome(),
                atividadeDTOPut.descricao(),
                atividadeDTOPut.distancia(),
                atividadeDTOPut.tempo(),
                atividadeDTOPut.data(),
                atividadeDTOPut.tipoBicicleta(),
                atividadeDTOPut.publica()
        ));
        return p != null ?
                ResponseEntity.ok(new AtividadeDtoResponse(p)):
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}