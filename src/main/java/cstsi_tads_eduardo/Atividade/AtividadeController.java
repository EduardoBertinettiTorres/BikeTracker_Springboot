package cstsi_tads_eduardo.Atividade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/atividades")
public class AtividadeController {

    private final AtividadeRepository repository;

    public AtividadeController(AtividadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> findAll(){
        return ResponseEntity.ok(repository.findAll());
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
}