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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String biografia;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rota> rotas;

    @ManyToMany(mappedBy = "usuarios")
    private List<Grupo> grupos;
}

