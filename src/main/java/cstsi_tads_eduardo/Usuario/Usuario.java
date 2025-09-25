package cstsi_tads_eduardo.Usuario;

import cstsi_tads_eduardo.Atividade.Atividade;
import cstsi_tads_eduardo.Grupo.Grupo;
import cstsi_tads_eduardo.Rota.Rota;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true, nullable = false) // Email deve ser único e não nulo
    private String email;

    @Column(nullable = false) // Senha não deve ser nula
    private String senha;

    private String biografia;

    // Lado inverso da relação. A entidade 'Atividade' gerencia esta relação
    // através do campo 'usuario'.
    // Cascade e orphanRemoval podem ser úteis para gerenciar o ciclo de vida das atividades
    // quando um usuário é removido.
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;

    // Lado inverso da relação. A entidade 'Rota' gerencia esta relação
    // através do campo 'usuario'.
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rota> rotas;

    // Lado inverso da relação ManyToMany. A entidade 'Grupo' gerencia a tabela de junção.
    @ManyToMany(mappedBy = "usuarios")
    private List<Grupo> grupos;
}
