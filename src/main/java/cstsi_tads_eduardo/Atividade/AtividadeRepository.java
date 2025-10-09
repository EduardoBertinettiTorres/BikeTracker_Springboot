package cstsi_tads_eduardo.Atividade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    //@Query(value = "SELECT p FROM Produto p where p.nome like ?1 order by p.nome")
    @Query(value = "select * from atividades p where p.nome like ?1 order by p.nome", nativeQuery=true)
    List<Atividade> findByNome(String nome);
}