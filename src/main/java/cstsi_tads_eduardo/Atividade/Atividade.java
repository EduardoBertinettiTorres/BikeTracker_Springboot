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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rota_id")
    private Rota rota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}