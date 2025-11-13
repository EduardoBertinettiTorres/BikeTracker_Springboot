package cstsi_tads_eduardo.Atividade;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Atividade}
 */
public record AtividadeDtoResponse(String nome, String descricao,
                                   double distancia, double tempo) implements Serializable {

    public AtividadeDtoResponse(Atividade atividade) {
        this(atividade.getNome(), atividade.getDescricao(), atividade.getDistancia(), atividade.getTempo());
    }
}
