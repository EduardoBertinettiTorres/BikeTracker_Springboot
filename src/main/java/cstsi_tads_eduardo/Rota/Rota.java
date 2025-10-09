package cstsi_tads_eduardo.Rota;

import cstsi_tads_eduardo.Atividade.Atividade;
import cstsi_tads_eduardo.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Rota")
@Table(name = "rotas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private double distancia;
    private double elevacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "rota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;
}
