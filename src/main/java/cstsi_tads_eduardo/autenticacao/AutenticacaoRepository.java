package cstsi_tads_eduardo.autenticacao;

import cstsi_tads_eduardo.Usuario.Usuario;
import org.springframework.data.repository.Repository;

public interface AutenticacaoRepository extends Repository<Usuario,Long> {
    Usuario findByEmail(String email);
}
