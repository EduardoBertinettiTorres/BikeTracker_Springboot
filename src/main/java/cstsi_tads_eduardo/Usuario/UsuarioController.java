package cstsi_tads_eduardo.Usuario;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@RestController
//Indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean da camada de controle API REST
//Note que @RequestMapping("api/v1/usuarios") foi propositalmente omitido nessa classe. Assim não será exposto o endpoint ao confirmar um email no navegador.
public class UsuarioController {
    private final UsuarioRepository repository;
    private final PerfilRepository perfilRepository;

    //indica ao Spring Boot que ele deve injetar estas dependências para a classe funcionar
    public UsuarioController(UsuarioRepository repository, PerfilRepository perfilRepository) {
        this.repository = repository;
        this.perfilRepository = perfilRepository;
    }

    @PostMapping(path = "/api/v1/usuarios/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioDTO usuarioDTO, UriComponentsBuilder uriBuilder) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); //leia o comentário acima da classe para entender mais
        var usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setSobrenome(usuarioDTO.sobrenome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(encoder.encode(usuarioDTO.senha()));
        if(usuarioDTO.perfil().equals("ROLE_ADMIN"))
            usuario.setPerfis(Arrays.asList(perfilRepository.findByNome("ROLE_ADMIN"), perfilRepository.findByNome("ROLE_USER")));
        else if(usuarioDTO.perfil().equals("ROLE_USER"))
            usuario.setPerfis(Arrays.asList(perfilRepository.findByNome("ROLE_USER")));

        var u = repository.save(usuario);
        var location = uriBuilder.path("api/v1/usuarios/cadastrar/{id}").buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(location).build();

    }
}
