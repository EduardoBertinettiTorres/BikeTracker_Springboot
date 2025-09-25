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

    // Lado "dono" da relação com Usuario. A tabela 'rotas' terá a coluna 'usuario_id'.
    @ManyToOne(fetch = FetchType.LAZY) // LAZY é o padrão e geralmente a melhor escolha
    @JoinColumn(name = "usuario_id", nullable = false) // Rotas devem pertencer a um usuário
    private Usuario usuario;

    // Lado inverso da relação com Atividade.
    @OneToMany(mappedBy = "rota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;
}
