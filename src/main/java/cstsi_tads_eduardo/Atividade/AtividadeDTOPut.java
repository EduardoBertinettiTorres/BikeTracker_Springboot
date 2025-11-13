package cstsi_tads_eduardo.Atividade;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;

public record AtividadeDTOPut(
        @NotBlank(message = "O nome não pode ser nulo ou vazio") //verifica se está vazio e estabelece como obrigatório (não pode ser nulo)
        @Size(min = 2, max = 50, message = "Tamanho mínimo de 2 e máximo de 200")
        String nome,
        @NotBlank(message = "A descrição não pode ser nula ou vazio") //verifica se está vazio e estabelece como obrigatório (não pode ser nulo)
        @Size(min = 2, max = 255, message = "Tamanho mínimo de 2 e máximo de 255")
        String descricao,
        @NotNull
        @Min(0)
        double distancia,
        @NotNull
        @Min(0)
        double tempo,
        @NotNull
        @Min(0)
        Date data,
        @NotNull
        @Min(0)
        String tipoBicicleta,
        @NotNull
        @Min(0)
        boolean publica
) {}
