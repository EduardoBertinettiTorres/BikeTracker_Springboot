package cstsi_tads_eduardo.Atividade;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

public record AtividadeDTOPost(

        @NotBlank(message = "O nome não pode ser nulo ou vazio")
        @Size(min = 2, max = 50, message = "Tamanho mínimo de 2 e máximo de 50")
        String nome,

        @NotBlank(message = "A descrição não pode ser nula ou vazia")
        @Size(min = 2, max = 255, message = "Tamanho mínimo de 2 e máximo de 255")
        String descricao,

        @NotNull
        @Min(0)
        double distancia,

        @NotNull
        @Min(0)
        double tempo,

        @NotNull
        // Removido @Min(0) pois é uma Data
        Date data,

        @NotNull
        // Removido @Min(0) pois é String
        String tipoBicicleta,

        @NotNull
        // Removido @Min(0) pois é boolean
        boolean publica
) {}