package cstsi_tads_eduardo.Grupo;

import cstsi_tads_eduardo.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "Grupo")
@Table(name = "grupos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    // Este é o lado "dono" da relação ManyToMany.
    // A anotação @JoinTable define a tabela de junção e as colunas de chave estrangeira.
    @ManyToMany(fetch = FetchType.EAGER) // EAGER pode ser útil aqui, mas use com cuidado em grandes listas
    @JoinTable(
            name = "grupos_usuarios", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "grupo_id"), // Chave estrangeira para Grupo
            inverseJoinColumns = @JoinColumn(name = "usuario_id") // Chave estrangeira para Usuario
    )
    private List<Usuario> usuarios;

    private Long membros;
    private Date dataCriacao;
    private boolean publico;
    private Long admins;
}
