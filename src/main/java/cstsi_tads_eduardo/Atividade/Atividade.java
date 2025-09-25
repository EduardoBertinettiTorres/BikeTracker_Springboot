package cstsi_tads_eduardo.Atividade;

import cstsi_tads_eduardo.Rota.Rota;
import cstsi_tads_eduardo.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "Atividade")
@Table(name = "atividades")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double distancia;
    private double tempo;
    private Date data;
    private String tipoBicicleta;
    private boolean publica;

    // Lado "dono" da relação com Rota. A tabela 'atividades' terá a coluna 'rota_id'.
    // Este relacionamento pode ser opcional (uma atividade pode não estar ligada a uma rota pré-definida).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rota_id") // nullable = true por padrão, o que permite atividades sem rota
    private Rota rota;

    // Lado "dono" da relação com Usuario. A tabela 'atividades' terá a coluna 'usuario_id'.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false) // Toda atividade DEVE ter um usuário
    private Usuario usuario;
}
