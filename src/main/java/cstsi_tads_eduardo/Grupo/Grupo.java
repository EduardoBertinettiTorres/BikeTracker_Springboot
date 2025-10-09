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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "grupos_usuarios",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

    private Long membros;
    private Date dataCriacao;
    private boolean publico;
    private Long admins;
}
